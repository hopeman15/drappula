package com.hellocuriosity.drappula.themes

object DraculaTheme : Theme {
    private const val DEEP_PURPLE = "2d1b4e"
    private const val BURGUNDY = "4a1c2e"
    private const val BONE_WHITE = "f0e6d3"

    override val name: String = "Dracula"

    override val typography: Typography = Typography.Default

    override val darkColors: ThemeColor =
        ThemeColor(
            primary = DEEP_PURPLE,
            secondary = BURGUNDY,
            background = DEEP_PURPLE,
            surface = BURGUNDY,
            onPrimary = BONE_WHITE,
            onBackground = BONE_WHITE,
            onSurface = BONE_WHITE,
            gradientStart = DEEP_PURPLE,
            gradientEnd = BURGUNDY,
        )

    override val lightColors: ThemeColor =
        ThemeColor(
            primary = DEEP_PURPLE,
            secondary = BURGUNDY,
            background = DEEP_PURPLE,
            surface = BURGUNDY,
            onPrimary = BONE_WHITE,
            onBackground = BONE_WHITE,
            onSurface = BONE_WHITE,
            gradientStart = DEEP_PURPLE,
            gradientEnd = BURGUNDY,
        )
}
