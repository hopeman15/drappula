# Project Context

## Overview
Drappula is a Kotlin Multiplatform (KMP) "Vampire Sound Machine" app targeting Android and iOS.

## Tech Stack
- **Language**: Kotlin (Multiplatform)
- **UI**: Jetpack Compose (Android), SwiftUI (iOS)
- **Build**: Gradle with Kotlin DSL
- **Static Analysis**: Detekt
- **Formatting**: Kotlinter
- **Code Coverage**: Kover
- **CI/CD**: GitHub Actions

## Project Structure
- `shared/` - Common KMP module with shared business logic
  - `commonMain/` - Shared code for all platforms
  - `androidMain/` - Android-specific implementations
  - `iosMain/` - iOS-specific implementations
  - `commonTest/` - Shared tests
- `android/` - Android app module
- `ios/` - iOS app (Xcode project)

## Package Naming
- Base package: `com.hellocuriosity.drappula`

## Build Commands (using make)
This project uses `make` as its command runner.

### Common Commands
- `make all` - Run all quality gates (clean, format, lint, test, report, assemble)
- `make build` - Build all platforms
- `make format` - Format all Kotlin code
- `make lint` - Run all linters (Kotlinter, Detekt, Android Lint)
- `make test` - Run all tests
- `make report` - Generate code coverage reports
- `make clean` - Clean build outputs

### Platform-Specific
- `make build-android` - Build Android app
- `make build-ios` - Build iOS framework
- `make test-android` - Run Android tests
- `make test-shared` - Run shared module tests
- `make ci` - Run full CI pipeline locally

---

# Kotlin Guidelines

## Kotlin Multiplatform Patterns

### Platform-Specific Code
Use `expect`/`actual` declarations for platform differences:

```kotlin
// In commonMain
expect fun getPlatformName(): String

// In androidMain
actual fun getPlatformName(): String = "Android"

// In iosMain
actual fun getPlatformName(): String = "iOS"
```

### Dependency Injection
Place shared dependencies in `commonMain.dependencies` block in `shared/build.gradle.kts`.

## Code Quality
- Run `make format` before committing
- Run `make lint` to check for code smells (runs Kotlinter, Detekt, and Android Lint)
- Follow Kotlinter formatting rules
- Follow Detekt rules defined in `detekt/detekt.yml`
- Use JVM target 19 for Android
- Prefer `expect`/`actual` declarations for platform-specific code

## Testing
- Write tests in `commonTest` for shared logic
- Use `kotlin.test` assertions
- Test file naming: `*Test.kt`

---

# Gradle Guidelines

## Version Catalog
All dependencies and versions are managed in `gradle/libs.versions.toml`.

When adding new dependencies or plugins:
1. Add version to `[versions]` section
2. Add library to `[libraries]` section
3. Add plugins to `[plugins]` section
4. Reference using `libs.` in build.gradle.kts files
