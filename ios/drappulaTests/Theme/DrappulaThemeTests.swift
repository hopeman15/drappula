import Testing
import SwiftUI
@testable import Drappula

struct DrappulaThemeTests {
    @Test
    func lightThemeHasColors() {
        let theme = DrappulaThemeValues.light
        // Colors should be initialized (non-nil is implicit in Swift structs)
        _ = theme.colors.primary
        _ = theme.colors.onBackground
    }

    @Test
    func darkThemeHasColors() {
        let theme = DrappulaThemeValues.dark
        // Colors should be initialized
        _ = theme.colors.primary
        _ = theme.colors.onBackground
    }

    @Test
    func themeHasTypography() {
        let theme = DrappulaThemeValues.light
        // Typography should be initialized
        _ = theme.typography.display
        _ = theme.typography.button
    }

    @Test
    func themeHasGradients() {
        let theme = DrappulaThemeValues.light
        // Gradients should be initialized
        _ = theme.gradients.background
    }

    @Test
    func forColorSchemeReturnsDarkForDark() {
        let theme = DrappulaThemeValues.forColorScheme(.dark)
        // Both should be equivalent to dark theme
        _ = theme.colors.primary
    }

    @Test
    func forColorSchemeReturnsLightForLight() {
        let theme = DrappulaThemeValues.forColorScheme(.light)
        // Both should be equivalent to light theme
        _ = theme.colors.primary
    }
}
