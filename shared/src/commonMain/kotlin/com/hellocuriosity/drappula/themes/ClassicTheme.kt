package com.hellocuriosity.drappula.themes

object ClassicTheme : Theme {
    private const val DEEP_BLACK = "0a0a0a"
    private const val CHARCOAL_GRAY = "4a4a4a"
    private const val SILVER_WHITE = "c8c8c8"

    override val name: String = "Classic"

    override val typography: Typography = Typography.Default

    override val darkColors: ThemeColor =
        ThemeColor(
            primary = DEEP_BLACK,
            secondary = CHARCOAL_GRAY,
            background = DEEP_BLACK,
            surface = CHARCOAL_GRAY,
            onPrimary = SILVER_WHITE,
            onBackground = SILVER_WHITE,
            onSurface = SILVER_WHITE,
            gradientStart = DEEP_BLACK,
            gradientEnd = CHARCOAL_GRAY,
        )

    override val lightColors: ThemeColor =
        ThemeColor(
            primary = DEEP_BLACK,
            secondary = CHARCOAL_GRAY,
            background = DEEP_BLACK,
            surface = CHARCOAL_GRAY,
            onPrimary = SILVER_WHITE,
            onBackground = SILVER_WHITE,
            onSurface = SILVER_WHITE,
            gradientStart = DEEP_BLACK,
            gradientEnd = CHARCOAL_GRAY,
        )
}
