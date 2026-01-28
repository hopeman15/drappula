package com.hellocuriosity.drappula.themes

import kotlin.test.Test
import kotlin.test.assertEquals

class DraculaThemeTest : BaseThemeTest() {
    override val theme: Theme = DraculaTheme
    override val name: String = "Dracula"
    override val darkColors: ThemeColor =
        ThemeColor(
            primary = "2d1b4e",
            secondary = "4a1c2e",
            background = "2d1b4e",
            surface = "4a1c2e",
            onPrimary = "f0e6d3",
            onBackground = "f0e6d3",
            onSurface = "f0e6d3",
            gradientStart = "2d1b4e",
            gradientEnd = "4a1c2e",
        )
    override val lightColors: ThemeColor =
        ThemeColor(
            primary = "2d1b4e",
            secondary = "4a1c2e",
            background = "2d1b4e",
            surface = "4a1c2e",
            onPrimary = "f0e6d3",
            onBackground = "f0e6d3",
            onSurface = "f0e6d3",
            gradientStart = "2d1b4e",
            gradientEnd = "4a1c2e",
        )

    @Test
    fun runAllTests() = verifyAll()

    @Test
    fun themeHasTypography() {
        assertEquals("Cinzel", DraculaTheme.typography.fontFamily)
    }

    @Test
    fun typographyHasCorrectTextStyles() {
        val typography = DraculaTheme.typography
        assertEquals(32, typography.display.size)
        assertEquals(FontWeight.BOLD, typography.display.weight)
        assertEquals(24, typography.headline.size)
        assertEquals(FontWeight.BOLD, typography.headline.weight)
        assertEquals(20, typography.title.size)
        assertEquals(FontWeight.BOLD, typography.title.weight)
        assertEquals(16, typography.body.size)
        assertEquals(FontWeight.NORMAL, typography.body.weight)
        assertEquals(16, typography.button.size)
        assertEquals(FontWeight.MEDIUM, typography.button.weight)
        assertEquals(12, typography.caption.size)
        assertEquals(FontWeight.NORMAL, typography.caption.weight)
    }
}
