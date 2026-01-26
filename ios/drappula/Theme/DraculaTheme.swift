import shared
import SwiftUI

enum DraculaTheme {
    private static let sharedTheme = shared.DraculaTheme.shared

    static func colors(for colorScheme: ColorScheme) -> ThemeColors {
        colorScheme == .dark ? sharedTheme.darkColors : sharedTheme.lightColors
    }

    static func backgroundGradient(for colorScheme: ColorScheme) -> LinearGradient {
        let themeColors = colors(for: colorScheme)
        return LinearGradient(
            colors: [
                Color(hex: themeColors.gradientStart),
                Color(hex: themeColors.gradientEnd)
            ],
            startPoint: .top,
            endPoint: .bottom
        )
    }

    static func buttonGradient(for colorScheme: ColorScheme) -> LinearGradient {
        let themeColors = colors(for: colorScheme)
        return LinearGradient(
            colors: [
                Color(hex: themeColors.gradientStart),
                Color(hex: themeColors.gradientEnd)
            ],
            startPoint: .top,
            endPoint: .bottom
        )
    }
}
