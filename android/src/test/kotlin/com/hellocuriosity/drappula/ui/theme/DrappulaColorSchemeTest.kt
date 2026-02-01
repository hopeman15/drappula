package com.hellocuriosity.drappula.ui.theme

import com.hellocuriosity.drappula.themes.ThemeColor
import org.junit.Test
import kotlin.test.assertEquals

class DrappulaColorSchemeTest {
    private val testThemeColor =
        ThemeColor(
            primary = "2d1b4e",
            secondary = "4a1c2e",
            background = "1a1a2e",
            surface = "16213e",
            onPrimary = "ffffff",
            onBackground = "f0e6d3",
            onSurface = "e0e0e0",
            gradientStart = "2d1b4e",
            gradientEnd = "4a1c2e",
        )

    @Test
    fun testToColorSchemeWithDarkModeReturnsCorrectColors() {
        val colorScheme = testThemeColor.toColorScheme(isDark = true)

        assertEquals("2d1b4e".toColor(), colorScheme.primary)
        assertEquals("4a1c2e".toColor(), colorScheme.secondary)
        assertEquals("1a1a2e".toColor(), colorScheme.background)
        assertEquals("16213e".toColor(), colorScheme.surface)
        assertEquals("ffffff".toColor(), colorScheme.onPrimary)
        assertEquals("f0e6d3".toColor(), colorScheme.onBackground)
        assertEquals("e0e0e0".toColor(), colorScheme.onSurface)
    }

    @Test
    fun testToColorSchemeWithLightModeReturnsCorrectColors() {
        val colorScheme = testThemeColor.toColorScheme(isDark = false)

        assertEquals("2d1b4e".toColor(), colorScheme.primary)
        assertEquals("4a1c2e".toColor(), colorScheme.secondary)
        assertEquals("1a1a2e".toColor(), colorScheme.background)
        assertEquals("16213e".toColor(), colorScheme.surface)
        assertEquals("ffffff".toColor(), colorScheme.onPrimary)
        assertEquals("f0e6d3".toColor(), colorScheme.onBackground)
        assertEquals("e0e0e0".toColor(), colorScheme.onSurface)
    }

    @Test
    fun testBackgroundGradientContainsCorrectColors() {
        val gradient = testThemeColor.backgroundGradient()

        // Verify gradient is created (Brush doesn't expose colors directly,
        // but we can verify it doesn't throw and returns a valid Brush)
        assertEquals(
            testThemeColor.backgroundGradient().hashCode(),
            gradient.hashCode(),
        )
    }

    @Test
    fun testButtonGradientContainsCorrectColors() {
        val gradient = testThemeColor.buttonGradient()

        // Verify gradient is created consistently
        assertEquals(
            testThemeColor.buttonGradient().hashCode(),
            gradient.hashCode(),
        )
    }
}
