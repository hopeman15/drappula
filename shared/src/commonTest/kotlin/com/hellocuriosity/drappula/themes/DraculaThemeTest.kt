package com.hellocuriosity.drappula.themes

import kotlin.test.Test

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
    override val typography: Typography = Typography.Default

    @Test
    fun runAllTests() = verifyAll()
}
