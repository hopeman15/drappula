package com.hellocuriosity.drappula.themes

import kotlin.test.assertEquals

abstract class BaseThemeTest {
    abstract val theme: Theme

    abstract val name: String

    abstract val darkColors: ThemeColor
    abstract val lightColors: ThemeColor

    fun verifyAll() {
        verifyDarkTheme()
        verifyLightTheme()
        verifyName()
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
}
