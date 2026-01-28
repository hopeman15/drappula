import shared
import SwiftUI

// MARK: - Theme Environment Key

struct DrappulaThemeKey: EnvironmentKey {
    static let defaultValue: DrappulaThemeValues = DrappulaThemeValues.light
}

extension EnvironmentValues {
    var drappulaTheme: DrappulaThemeValues {
        get { self[DrappulaThemeKey.self] }
        set { self[DrappulaThemeKey.self] = newValue }
    }
}

// MARK: - Theme Values

struct DrappulaThemeValues {
    let colors: DrappulaColors
    let typography: DrappulaTypography
    let gradients: DrappulaGradients

    static let light = DrappulaThemeValues(
        colors: DrappulaColors(themeColors: DraculaTheme.shared.lightColors),
        typography: DrappulaTypography(),
        gradients: DrappulaGradients(themeColors: DraculaTheme.shared.lightColors)
    )

    static let dark = DrappulaThemeValues(
        colors: DrappulaColors(themeColors: DraculaTheme.shared.darkColors),
        typography: DrappulaTypography(),
        gradients: DrappulaGradients(themeColors: DraculaTheme.shared.darkColors)
    )

    static func forColorScheme(_ colorScheme: ColorScheme) -> DrappulaThemeValues {
        colorScheme == .dark ? .dark : .light
    }
}

// MARK: - Colors

struct DrappulaColors {
    let primary: Color
    let secondary: Color
    let background: Color
    let surface: Color
    let onPrimary: Color
    let onBackground: Color
    let onSurface: Color

    init(themeColors: ThemeColors) {
        self.primary = Color(hex: themeColors.primary)
        self.secondary = Color(hex: themeColors.secondary)
        self.background = Color(hex: themeColors.background)
        self.surface = Color(hex: themeColors.surface)
        self.onPrimary = Color(hex: themeColors.onPrimary)
        self.onBackground = Color(hex: themeColors.onBackground)
        self.onSurface = Color(hex: themeColors.onSurface)
    }
}

// MARK: - Typography

struct DrappulaTypography {
    let display: Font
    let headline: Font
    let title: Font
    let body: Font
    let button: Font
    let caption: Font

    init() {
        self.display = .custom("Cinzel-Bold", size: 32)
        self.headline = .custom("Cinzel-Bold", size: 24)
        self.title = .custom("Cinzel-Bold", size: 20)
        self.body = .custom("Cinzel-Regular", size: 16)
        self.button = .custom("Cinzel-Medium", size: 16)
        self.caption = .custom("Cinzel-Regular", size: 12)
    }
}

// MARK: - Gradients

struct DrappulaGradients {
    let background: LinearGradient
    let button: LinearGradient

    init(themeColors: ThemeColors) {
        let gradientColors = [
            Color(hex: themeColors.gradientStart),
            Color(hex: themeColors.gradientEnd)
        ]
        self.background = LinearGradient(
            colors: gradientColors,
            startPoint: .top,
            endPoint: .bottom
        )
        self.button = LinearGradient(
            colors: gradientColors,
            startPoint: .top,
            endPoint: .bottom
        )
    }
}

// MARK: - Theme View Modifier

struct DrappulaThemeModifier: ViewModifier {
    @Environment(\.colorScheme) private var colorScheme

    func body(content: Content) -> some View {
        content
            .environment(\.drappulaTheme, DrappulaThemeValues.forColorScheme(colorScheme))
    }
}

extension View {
    func drappulaTheme() -> some View {
        modifier(DrappulaThemeModifier())
    }
}
