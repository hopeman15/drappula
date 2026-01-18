# Default build type
build_type := "Debug"

# iOS simulator for testing - override with IOS_SIMULATOR env var
# Use 'xcrun simctl list devices available' to see options
ios_simulator := env("IOS_SIMULATOR", "iPhone 17")

# Default recipe - runs all quality gates
all: clean format lint test report assemble

# ─────────────────────────────────────────────────────────────────────────────
# Assemble & Bundle
# ─────────────────────────────────────────────────────────────────────────────

# Assemble all modules
assemble:
    ./gradlew assemble{{build_type}}

# Assemble Android app
assemble-android:
    ./gradlew :android:assemble{{build_type}}

# Bundle all modules
bundle:
    ./gradlew bundle{{build_type}}

# Bundle Android app
bundle-android:
    ./gradlew :android:bundle{{build_type}}

# ─────────────────────────────────────────────────────────────────────────────
# Build
# ─────────────────────────────────────────────────────────────────────────────

# Build all platforms
build: build-android build-ios

# Build Android app
build-android:
    ./gradlew :android:build{{build_type}}

# Build shared module
build-shared:
    ./gradlew :shared:build

# Build iOS framework
build-ios:
    ./gradlew :shared:linkDebugFrameworkIosSimulatorArm64 :shared:linkDebugFrameworkIosArm64

# Build iOS native app
build-ios-native:
    xcodebuild -project ios/drappula.xcodeproj -scheme drappula -sdk iphonesimulator -destination 'generic/platform=iOS Simulator' -configuration Debug build

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
    ./gradlew lintKotlin detekt lint{{build_type}}

# Lint Android module
lint-android:
    ./gradlew :android:lintKotlin :android:detekt :android:lint{{build_type}}

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
test: test-android test-shared

# Run Android unit tests
test-android:
    ./gradlew :android:test{{build_type}}UnitTest

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
report-shared:
    ./gradlew :shared:koverHtmlReport :shared:koverXmlReport

# Generate iOS coverage report (runs tests first)
report-ios: test-ios
    xcrun xccov view --report --json build/ios/results.xcresult > build/ios/coverage.json
    xcrun xccov view --report build/ios/results.xcresult

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
