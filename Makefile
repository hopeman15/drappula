# Variables - use ?= so environment variables and command-line overrides take precedence
BUILD_TYPE ?= Debug
FLAVOR ?= Staging
IOS_FLAVOR ?= Staging
IOS_SCHEME = Drappula ($(IOS_FLAVOR))

# iOS simulator for testing - override with IOS_SIMULATOR env var
IOS_SIMULATOR ?= iPhone 17

# iOS signing - set via environment variables or .env file
TEAM_ID ?=
PROVISIONING_PROFILE_NAME ?=
MARKETING_VERSION ?= 1.0
BUILD_NUMBER ?= 1

# Default target - runs all quality gates
all: clean validate-renovate format lint test report assemble
.PHONY: all

# ─────────────────────────────────────────────────────────────────────────────
# Assets
# ─────────────────────────────────────────────────────────────────────────────

# Sync audio symlinks for Android assets
asset-sync:
	./scripts/asset-sync.sh
.PHONY: asset-sync

# ─────────────────────────────────────────────────────────────────────────────
# Assemble & Bundle
# ─────────────────────────────────────────────────────────────────────────────

# Assemble all modules
assemble:
	./gradlew assemble$(FLAVOR)$(BUILD_TYPE)
.PHONY: assemble

# Assemble Android app
assemble-android:
	./gradlew :android:assemble$(FLAVOR)$(BUILD_TYPE)
.PHONY: assemble-android

# Bundle all modules
bundle:
	./gradlew bundle$(FLAVOR)$(BUILD_TYPE)
.PHONY: bundle

# Bundle Android app
bundle-android:
	./gradlew :android:bundle$(FLAVOR)$(BUILD_TYPE)
.PHONY: bundle-android

# ─────────────────────────────────────────────────────────────────────────────
# Build
# ─────────────────────────────────────────────────────────────────────────────

# Build all platforms
build: build-android build-ios
.PHONY: build

# Build Android app
build-android:
	./gradlew :android:build$(FLAVOR)$(BUILD_TYPE)
.PHONY: build-android

# Build shared module
build-shared:
	./gradlew :shared:build
.PHONY: build-shared

# Build iOS framework
build-ios:
	./gradlew :shared:linkDebugFrameworkIosSimulatorArm64 :shared:linkDebugFrameworkIosArm64
.PHONY: build-ios

# Build iOS simulator framework only
build-ios-simulator:
	./gradlew :shared:linkDebugFrameworkIosSimulatorArm64
.PHONY: build-ios-simulator

# Build iOS native app
build-ios-native:
	xcodebuild -project ios/drappula.xcodeproj -scheme '$(IOS_SCHEME)' -sdk iphonesimulator -destination 'generic/platform=iOS Simulator' -configuration $(IOS_FLAVOR)Debug build
.PHONY: build-ios-native

# Archive iOS app for distribution (requires TEAM_ID, PROVISIONING_PROFILE_NAME, MARKETING_VERSION, BUILD_NUMBER env vars)
archive-ios:
	./gradlew :shared:linkReleaseFrameworkIosArm64
	xcodebuild -project ios/drappula.xcodeproj -scheme '$(IOS_SCHEME)' -configuration $(IOS_FLAVOR)Release -sdk iphoneos -destination 'generic/platform=iOS' -archivePath build/ios/drappula.xcarchive MARKETING_VERSION=$(MARKETING_VERSION) CURRENT_PROJECT_VERSION=$(BUILD_NUMBER) DEVELOPMENT_TEAM=$(TEAM_ID) PROVISIONING_PROFILE_SPECIFIER="$(PROVISIONING_PROFILE_NAME)" archive
.PHONY: archive-ios

# Export iOS archive to IPA (requires TEAM_ID and PROVISIONING_PROFILE_NAME env vars)
export-ios:
	./scripts/generate-export-options.sh
	xcodebuild -exportArchive -archivePath build/ios/drappula.xcarchive -exportPath build/ios/output -exportOptionsPlist build/ios/ExportOptions.plist
.PHONY: export-ios

# ─────────────────────────────────────────────────────────────────────────────
# Clean
# ─────────────────────────────────────────────────────────────────────────────

# Clean Gradle build
clean:
	./gradlew clean
.PHONY: clean

# Clean iOS build
clean-ios:
	xcodebuild -project ios/drappula.xcodeproj -scheme '$(IOS_SCHEME)' clean
.PHONY: clean-ios

# Clean all builds
clean-all: clean clean-ios
.PHONY: clean-all

# ─────────────────────────────────────────────────────────────────────────────
# Format
# ─────────────────────────────────────────────────────────────────────────────

# Format all Kotlin code
format:
	./gradlew formatKotlin
.PHONY: format

# ─────────────────────────────────────────────────────────────────────────────
# Lint
# ─────────────────────────────────────────────────────────────────────────────

# Lint all modules
lint: lint-other lint-android lint-ios lint-shared
.PHONY: lint

lint-other:
	./gradlew lintKotlin detekt lint$(FLAVOR)$(BUILD_TYPE)
.PHONY: lint-other

# Lint Android module
lint-android:
	./gradlew :android:lintKotlin :android:detekt :android:lint$(FLAVOR)$(BUILD_TYPE)
.PHONY: lint-android

# Lint shared module (no Android Lint with com.android.kotlin.multiplatform.library)
lint-shared:
	./gradlew :shared:lintKotlin :shared:detekt
.PHONY: lint-shared

# Lint iOS code with SwiftLint
lint-ios:
	swiftlint lint ios/drappula --strict
.PHONY: lint-ios

# ─────────────────────────────────────────────────────────────────────────────
# Test
# ─────────────────────────────────────────────────────────────────────────────

# Run all tests
test: test-android test-shared test-ios
.PHONY: test

# Run Android unit tests
test-android:
	./gradlew :android:test$(FLAVOR)$(BUILD_TYPE)UnitTest
.PHONY: test-android

# Run all shared module tests
test-shared: test-shared-android test-shared-ios
.PHONY: test-shared

# Run shared module tests on Android (host test)
test-shared-android:
	./gradlew :shared:testAndroidHostTest
.PHONY: test-shared-android

# Run shared module tests on iOS
test-shared-ios:
	./gradlew :shared:cleanIosX64Test :shared:iosX64Test
.PHONY: test-shared-ios

# Run iOS native tests
test-ios:
	rm -rf build/ios/results.xcresult
	mkdir -p build/ios
	xcodebuild -project ios/drappula.xcodeproj -scheme '$(IOS_SCHEME)' -sdk iphonesimulator -destination 'platform=iOS Simulator,name=$(IOS_SIMULATOR)' -enableCodeCoverage YES -resultBundlePath build/ios/results.xcresult test
.PHONY: test-ios

# ─────────────────────────────────────────────────────────────────────────────
# Report (Code Coverage)
# ─────────────────────────────────────────────────────────────────────────────

# Generate all coverage reports
report:
	./gradlew koverHtmlReport koverXmlReport
.PHONY: report

# Generate Android coverage report
report-android:
	./gradlew :android:koverHtmlReport :android:koverXmlReport
.PHONY: report-android

# Generate shared module coverage report
# Kover disabled for shared module until it supports com.android.kotlin.multiplatform.library
# See: https://github.com/hopeman15/drappula/issues/11
report-shared:
	@echo "Kover is disabled for shared module - see issue #11"
.PHONY: report-shared

# Generate iOS coverage report (runs tests first)
report-ios: test-ios
	xcrun xccov view --report --json build/ios/results.xcresult > build/ios/coverage.json
	xcrun xccov view --report build/ios/results.xcresult
.PHONY: report-ios

# ─────────────────────────────────────────────────────────────────────────────
# Dependency Analysis (Health Checks)
# ─────────────────────────────────────────────────────────────────────────────

# Run dependency analysis health check
health:
	./gradlew projectHealth buildHealth
.PHONY: health

# ─────────────────────────────────────────────────────────────────────────────
# Validation
# ─────────────────────────────────────────────────────────────────────────────

# Validate Renovate configuration
validate-renovate:
	npx --yes --package renovate -- renovate-config-validator
.PHONY: validate-renovate

# ─────────────────────────────────────────────────────────────────────────────
# Publishing & Release
# ─────────────────────────────────────────────────────────────────────────────

# Publish to Play Store (requires KEY_STORE_GPG and PLAY_PUBLISH_PASSWORD env vars)
publish:
	./scripts/publish.sh $(FLAVOR) Release $${KEY_STORE_GPG} $${PLAY_PUBLISH_PASSWORD}
.PHONY: publish

# Publish iOS app to TestFlight (requires ASC_KEY_ID, ASC_ISSUER_ID, and API key file)
publish-ios:
	./scripts/publish-ios.sh
.PHONY: publish-ios

# Interactive local signing
signing:
	./scripts/signing.sh $(FLAVOR) Release
.PHONY: signing
