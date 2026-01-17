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

## Build Commands (using just)
This project uses [just](https://github.com/casey/just) as a command runner.

### Common Commands
- `just all` - Run all quality gates (clean, format, lint, test, report, assemble)
- `just build` - Build all platforms
- `just format` - Format all Kotlin code
- `just lint` - Run all linters (Kotlinter, Detekt, Android Lint)
- `just test` - Run all tests
- `just report` - Generate code coverage reports
- `just clean` - Clean build outputs

### Platform-Specific
- `just build-android` - Build Android app
- `just build-ios` - Build iOS framework
- `just test-android` - Run Android tests
- `just test-shared` - Run shared module tests
- `just ci` - Run full CI pipeline locally

### Direct Gradle (if just not available)
- `./gradlew build` - Build all modules
- `./gradlew formatKotlin` - Auto-format Kotlin code
- `./gradlew detekt lintKotlin` - Run static analysis

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
- Run `just format` before committing
- Run `just lint` to check for code smells (runs Kotlinter, Detekt, and Android Lint)
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
