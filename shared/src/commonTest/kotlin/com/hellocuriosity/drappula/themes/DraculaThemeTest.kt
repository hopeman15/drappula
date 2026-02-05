package com.hellocuriosity.drappula.themes

import kotlin.test.Test

class DraculaThemeTest : BaseThemeTest() {
    override val theme: Theme = DraculaTheme
    override val name: String = "Dracula"
    override val darkColors: ThemeColor =
        ThemeColor(
            primary = "0d0d0d",
            secondary = "d32f2f",
            background = "0d0d0d",
            surface = "d32f2f",
            onPrimary = "f0e6d3",
            onBackground = "f0e6d3",
            onSurface = "f0e6d3",
            gradientStart = "0d0d0d",
            gradientEnd = "d32f2f",
        )
    override val lightColors: ThemeColor =
        ThemeColor(
            primary = "0d0d0d",
            secondary = "d32f2f",
            background = "0d0d0d",
            surface = "d32f2f",
            onPrimary = "f0e6d3",
            onBackground = "f0e6d3",
            onSurface = "f0e6d3",
            gradientStart = "0d0d0d",
            gradientEnd = "d32f2f",
        )
    override val typography: Typography = Typography.Default

    @Test
    fun runAllTests() = verifyAll()
}
