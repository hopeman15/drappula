import Testing
import SwiftUI
@testable import Drappula
import shared

struct DraculaThemeTests {

    @Test
    func colorsForDarkSchemeReturnsDarkColors() {
        let colors = DraculaTheme.colors(for: .dark)
        let expected = shared.DraculaTheme.shared.darkColors

        #expect(colors.primary == expected.primary)
        #expect(colors.secondary == expected.secondary)
        #expect(colors.background == expected.background)
        #expect(colors.surface == expected.surface)
        #expect(colors.onPrimary == expected.onPrimary)
        #expect(colors.onBackground == expected.onBackground)
        #expect(colors.onSurface == expected.onSurface)
        #expect(colors.gradientStart == expected.gradientStart)
        #expect(colors.gradientEnd == expected.gradientEnd)
    }

    @Test
    func colorsForLightSchemeReturnsLightColors() {
        let colors = DraculaTheme.colors(for: .light)
        let expected = shared.DraculaTheme.shared.lightColors

        #expect(colors.primary == expected.primary)
        #expect(colors.secondary == expected.secondary)
        #expect(colors.background == expected.background)
        #expect(colors.surface == expected.surface)
        #expect(colors.onPrimary == expected.onPrimary)
        #expect(colors.onBackground == expected.onBackground)
        #expect(colors.onSurface == expected.onSurface)
        #expect(colors.gradientStart == expected.gradientStart)
        #expect(colors.gradientEnd == expected.gradientEnd)
    }
}
