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
        typography: DrappulaTypography(typography: DraculaTheme.shared.typography),
        gradients: DrappulaGradients(themeColors: DraculaTheme.shared.lightColors)
    )

    static let dark = DrappulaThemeValues(
        colors: DrappulaColors(themeColors: DraculaTheme.shared.darkColors),
        typography: DrappulaTypography(typography: DraculaTheme.shared.typography),
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

    init(themeColors: ThemeColor) {
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

    init(typography: Typography) {
        let fontFamily = typography.fontFamily
        self.display = Self.font(family: fontFamily, config: typography.display)
        self.headline = Self.font(family: fontFamily, config: typography.headline)
        self.title = Self.font(family: fontFamily, config: typography.title)
        self.body = Self.font(family: fontFamily, config: typography.body)
        self.button = Self.font(family: fontFamily, config: typography.button)
        self.caption = Self.font(family: fontFamily, config: typography.caption)
    }

    private static func font(family: String, config: TextStyleConfig) -> Font {
        let fontName = "\(family)-\(config.weight.fontSuffix)"
        return .custom(fontName, size: CGFloat(config.size))
    }
}

private extension FontWeight {
    var fontSuffix: String {
        switch self {
        case .normal: return "Regular"
        case .medium: return "Medium"
        case .bold: return "Bold"
        default: return "Regular"
        }
    }
}

// MARK: - Gradients

struct DrappulaGradients {
    let background: LinearGradient

    init(themeColors: ThemeColor) {
        let gradientColors = [
            Color(hex: themeColors.gradientStart),
            Color(hex: themeColors.gradientEnd)
        ]
        self.background = LinearGradient(
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
        let theme = DrappulaThemeValues.forColorScheme(colorScheme)
        content
            .environment(\.drappulaTheme, theme)
            .tint(theme.colors.onBackground)
            .onAppear {
                configureTabBarAppearance(theme: theme)
            }
            .onChange(of: colorScheme) { _, _ in
                let updatedTheme = DrappulaThemeValues.forColorScheme(colorScheme)
                configureTabBarAppearance(theme: updatedTheme)
            }
    }

    private func configureTabBarAppearance(theme: DrappulaThemeValues) {
        let iconColor = UIColor(theme.colors.onBackground)

        let appearance = UITabBarAppearance()
        appearance.configureWithTransparentBackground()
        appearance.stackedLayoutAppearance.normal.iconColor = iconColor
        appearance.stackedLayoutAppearance.normal.titleTextAttributes = [
            .foregroundColor: iconColor
        ]
        appearance.stackedLayoutAppearance.selected.iconColor = iconColor
        appearance.stackedLayoutAppearance.selected.titleTextAttributes = [
            .foregroundColor: iconColor
        ]

        UITabBar.appearance().standardAppearance = appearance
        UITabBar.appearance().scrollEdgeAppearance = appearance
    }
}

extension View {
    func drappulaTheme() -> some View {
        modifier(DrappulaThemeModifier())
    }
}
