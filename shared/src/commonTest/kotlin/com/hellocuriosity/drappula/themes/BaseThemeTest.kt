package com.hellocuriosity.drappula.themes

import kotlin.test.assertEquals

abstract class BaseThemeTest {
    abstract val theme: Theme
    abstract val name: String
    abstract val darkColors: ThemeColor
    abstract val lightColors: ThemeColor
    abstract val typography: Typography

    fun verifyAll() {
        verifyDarkTheme()
        verifyLightTheme()
        verifyName()
        verifyTypography()
    }

    private fun verifyDarkTheme() {
        assertEquals(darkColors.primary, theme.darkColors.primary)
        assertEquals(darkColors.secondary, theme.darkColors.secondary)
        assertEquals(darkColors.onPrimary, theme.darkColors.onPrimary)
        assertEquals(darkColors.primary, theme.darkColors.gradientStart)
        assertEquals(darkColors.secondary, theme.darkColors.gradientEnd)
    }

    private fun verifyLightTheme() {
        assertEquals(lightColors.primary, theme.lightColors.primary)
        assertEquals(lightColors.secondary, theme.lightColors.secondary)
        assertEquals(lightColors.onPrimary, theme.lightColors.onPrimary)
        assertEquals(lightColors.primary, theme.lightColors.gradientStart)
        assertEquals(lightColors.secondary, theme.lightColors.gradientEnd)
    }

    private fun verifyName() {
        assertEquals(name, theme.name)
    }

    private fun verifyTypography() {
        assertEquals(typography.fontFamily, theme.typography.fontFamily)
        assertEquals(typography.display, theme.typography.display)
        assertEquals(typography.headline, theme.typography.headline)
        assertEquals(typography.title, theme.typography.title)
        assertEquals(typography.body, theme.typography.body)
        assertEquals(typography.button, theme.typography.button)
        assertEquals(typography.caption, theme.typography.caption)
    }
}
