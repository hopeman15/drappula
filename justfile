build_type := "Debug"
flavor := "Staging"

# iOS simulator for testing - override with IOS_SIMULATOR env var
# Use 'xcrun simctl list devices available' to see options
ios_simulator := env("IOS_SIMULATOR", "iPhone 17")

# iOS signing - set via environment variables or .env file
team_id := env("TEAM_ID", "")
provisioning_profile_name := env("PROVISIONING_PROFILE_NAME", "")
marketing_version := env("MARKETING_VERSION", "1.0")
build_number := env("BUILD_NUMBER", "1")

# Default recipe - runs all quality gates
all: clean validate-renovate format lint test report assemble

# ─────────────────────────────────────────────────────────────────────────────
# Assets
# ─────────────────────────────────────────────────────────────────────────────

# Sync audio symlinks for Android assets
asset-sync:
    #!/usr/bin/env bash
    set -euo pipefail
    ASSETS_AUDIO="android/src/main/assets/audio"
    mkdir -p "$ASSETS_AUDIO"
    for dir in audio/*/; do
        name=$(basename "$dir")
        link="$ASSETS_AUDIO/$name"
        if [ ! -L "$link" ]; then
            ln -s "../../../../../audio/$name" "$link"
            echo "Created symlink: $link"
        fi
    done

# ─────────────────────────────────────────────────────────────────────────────
# Assemble & Bundle
# ─────────────────────────────────────────────────────────────────────────────

# Assemble all modules
assemble:
    ./gradlew assemble{{flavor}}{{build_type}}

# Assemble Android app
assemble-android:
    ./gradlew :android:assemble{{flavor}}{{build_type}}

# Bundle all modules
bundle:
    ./gradlew bundle{{flavor}}{{build_type}}

# Bundle Android app
bundle-android:
    ./gradlew :android:bundle{{flavor}}{{build_type}}

# ─────────────────────────────────────────────────────────────────────────────
# Build
# ─────────────────────────────────────────────────────────────────────────────

# Build all platforms
build: build-android build-ios

# Build Android app
build-android:
    ./gradlew :android:build{{flavor}}{{build_type}}

# Build shared module
build-shared:
    ./gradlew :shared:build

# Build iOS framework
build-ios:
    ./gradlew :shared:linkDebugFrameworkIosSimulatorArm64 :shared:linkDebugFrameworkIosArm64

# Build iOS simulator framework only
build-ios-simulator:
    ./gradlew :shared:linkDebugFrameworkIosSimulatorArm64

# Build iOS native app
build-ios-native:
    xcodebuild -project ios/drappula.xcodeproj -scheme drappula -sdk iphonesimulator -destination 'generic/platform=iOS Simulator' -configuration Debug build

# Archive iOS app for distribution (requires TEAM_ID, PROVISIONING_PROFILE_NAME, MARKETING_VERSION, BUILD_NUMBER env vars)
archive-ios:
    ./gradlew :shared:linkReleaseFrameworkIosArm64
    xcodebuild -project ios/drappula.xcodeproj -scheme drappula -configuration Release -sdk iphoneos -destination 'generic/platform=iOS' -archivePath build/ios/drappula.xcarchive MARKETING_VERSION={{marketing_version}} CURRENT_PROJECT_VERSION={{build_number}} DEVELOPMENT_TEAM={{team_id}} PROVISIONING_PROFILE_SPECIFIER="{{provisioning_profile_name}}" archive

# Export iOS archive to IPA (requires TEAM_ID and PROVISIONING_PROFILE_NAME env vars)
export-ios:
    ./scripts/generate-export-options.sh
    xcodebuild -exportArchive -archivePath build/ios/drappula.xcarchive -exportPath build/ios/output -exportOptionsPlist build/ios/ExportOptions.plist

# ─────────────────────────────────────────────────────────────────────────────
# Clean
# ─────────────────────────────────────────────────────────────────────────────

# Clean Gradle build
clean:
    ./gradlew clean

# Clean iOS build
clean-ios:
    xcodebuild -project ios/drappula.xcodeproj -scheme drappula clean

# Clean all builds
clean-all: clean clean-ios

# ─────────────────────────────────────────────────────────────────────────────
# Format
# ─────────────────────────────────────────────────────────────────────────────

# Format all Kotlin code
format:
    ./gradlew formatKotlin

# ─────────────────────────────────────────────────────────────────────────────
# Lint
# ─────────────────────────────────────────────────────────────────────────────

# Lint all modules
lint: lint-other lint-android lint-ios lint-shared

lint-other:
    ./gradlew lintKotlin detekt lint{{flavor}}{{build_type}}

# Lint Android module
lint-android:
    ./gradlew :android:lintKotlin :android:detekt :android:lint{{flavor}}{{build_type}}

# Lint shared module (no Android Lint with com.android.kotlin.multiplatform.library)
lint-shared:
    ./gradlew :shared:lintKotlin :shared:detekt

# Lint iOS code with SwiftLint
lint-ios:
    swiftlint lint ios/drappula --strict

# ─────────────────────────────────────────────────────────────────────────────
# Test
# ─────────────────────────────────────────────────────────────────────────────

# Run all tests
test: test-android test-shared test-ios

# Run Android unit tests
test-android:
    ./gradlew :android:test{{flavor}}{{build_type}}UnitTest

# Run all shared module tests
test-shared: test-shared-android test-shared-ios

# Run shared module tests on Android (host test)
test-shared-android:
    ./gradlew :shared:testAndroidHostTest

# Run shared module tests on iOS
test-shared-ios:
    ./gradlew :shared:cleanIosX64Test :shared:iosX64Test

# Run iOS native tests
test-ios:
    rm -rf build/ios/results.xcresult
    mkdir -p build/ios
    xcodebuild -project ios/drappula.xcodeproj -scheme drappula -sdk iphonesimulator -destination 'platform=iOS Simulator,name={{ios_simulator}}' -enableCodeCoverage YES -resultBundlePath build/ios/results.xcresult test

# ─────────────────────────────────────────────────────────────────────────────
# Report (Code Coverage)
# ─────────────────────────────────────────────────────────────────────────────

# Generate all coverage reports
report:
    ./gradlew koverHtmlReport koverXmlReport

# Generate Android coverage report
report-android:
    ./gradlew :android:koverHtmlReport :android:koverXmlReport

# Generate shared module coverage report
# Kover disabled for shared module until it supports com.android.kotlin.multiplatform.library
# See: https://github.com/hopeman15/drappula/issues/11
report-shared:
    @echo "Kover is disabled for shared module - see issue #11"

# Generate iOS coverage report (runs tests first)
report-ios: test-ios
    xcrun xccov view --report --json build/ios/results.xcresult > build/ios/coverage.json
    xcrun xccov view --report build/ios/results.xcresult

# ─────────────────────────────────────────────────────────────────────────────
# Dependency Analysis (Health Checks)
# ─────────────────────────────────────────────────────────────────────────────

# Run dependency analysis health check
health:
    ./gradlew projectHealth buildHealth

# ─────────────────────────────────────────────────────────────────────────────
# Validation
# ─────────────────────────────────────────────────────────────────────────────

# Validate Renovate configuration
validate-renovate:
    npx --yes --package renovate -- renovate-config-validator

# ─────────────────────────────────────────────────────────────────────────────
# CI Workflows
# ─────────────────────────────────────────────────────────────────────────────

# Run Android CI workflow locally
ci-android: lint-android test-android report-android build-android

# Run shared module CI workflow locally
ci-shared: lint-shared test-shared report-shared build-shared

# Run iOS CI workflow locally
ci-ios: lint-ios report-ios build-ios build-ios-native

# Run all CI workflows
ci: ci-android ci-shared ci-ios

# ─────────────────────────────────────────────────────────────────────────────
# Publishing & Release
# ─────────────────────────────────────────────────────────────────────────────

# Publish to Play Store (requires KEY_STORE_GPG and PLAY_PUBLISH_PASSWORD env vars)
publish:
    ./scripts/publish.sh {{flavor}} Release ${KEY_STORE_GPG} ${PLAY_PUBLISH_PASSWORD}

# Publish iOS app to TestFlight (requires ASC_KEY_ID, ASC_ISSUER_ID, and API key file)
publish-ios:
    ./scripts/publish-ios.sh

# Interactive local signing
signing:
    ./scripts/signing.sh {{flavor}} Release
