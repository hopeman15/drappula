package com.hellocuriosity.drappula.themes

import kotlin.test.Test

class ClassicThemeTest : BaseThemeTest() {
    override val theme: Theme = ClassicTheme
    override val name: String = "Classic"
    override val darkColors: ThemeColor =
        ThemeColor(
            primary = "0a0a0a",
            secondary = "4a4a4a",
            background = "0a0a0a",
            surface = "4a4a4a",
            onPrimary = "c8c8c8",
            onBackground = "c8c8c8",
            onSurface = "c8c8c8",
            gradientStart = "0a0a0a",
            gradientEnd = "4a4a4a",
        )
    override val lightColors: ThemeColor =
        ThemeColor(
            primary = "0a0a0a",
            secondary = "4a4a4a",
            background = "0a0a0a",
            surface = "4a4a4a",
            onPrimary = "c8c8c8",
            onBackground = "c8c8c8",
            onSurface = "c8c8c8",
            gradientStart = "0a0a0a",
            gradientEnd = "4a4a4a",
        )
    override val typography: Typography = Typography.Default

    @Test
    fun runAllTests() = verifyAll()
}
