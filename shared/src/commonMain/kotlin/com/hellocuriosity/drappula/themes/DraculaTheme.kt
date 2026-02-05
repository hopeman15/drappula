package com.hellocuriosity.drappula.themes

object DraculaTheme : Theme {
    private const val DARK_BLACK = "0d0d0d"
    private const val BLOOD_RED = "d32f2f"
    private const val BONE_WHITE = "f0e6d3"

    override val name: String = "Dracula"

    override val typography: Typography = Typography.Default

    override val darkColors: ThemeColor =
        ThemeColor(
            primary = DARK_BLACK,
            secondary = BLOOD_RED,
            background = DARK_BLACK,
            surface = BLOOD_RED,
            onPrimary = BONE_WHITE,
            onBackground = BONE_WHITE,
            onSurface = BONE_WHITE,
            gradientStart = DARK_BLACK,
            gradientEnd = BLOOD_RED,
        )

    override val lightColors: ThemeColor =
        ThemeColor(
            primary = DARK_BLACK,
            secondary = BLOOD_RED,
            background = DARK_BLACK,
            surface = BLOOD_RED,
            onPrimary = BONE_WHITE,
            onBackground = BONE_WHITE,
            onSurface = BONE_WHITE,
            gradientStart = DARK_BLACK,
            gradientEnd = BLOOD_RED,
        )
}
