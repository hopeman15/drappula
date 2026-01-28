package com.hellocuriosity.drappula.themes

object DraculaTheme : Theme {
    private const val DEEP_PURPLE = "2d1b4e"
    private const val BURGUNDY = "4a1c2e"
    private const val BONE_WHITE = "f0e6d3"

    override val name: String = "Dracula"

    override val typography: Typography =
        object : Typography {
            override val fontFamily: String = "Cinzel"
            override val display: TextStyleConfig = TextStyleConfig(size = 32, weight = FontWeight.BOLD)
            override val headline: TextStyleConfig = TextStyleConfig(size = 24, weight = FontWeight.BOLD)
            override val title: TextStyleConfig = TextStyleConfig(size = 20, weight = FontWeight.BOLD)
            override val body: TextStyleConfig = TextStyleConfig(size = 16, weight = FontWeight.NORMAL)
            override val button: TextStyleConfig = TextStyleConfig(size = 16, weight = FontWeight.MEDIUM)
            override val caption: TextStyleConfig = TextStyleConfig(size = 12, weight = FontWeight.NORMAL)
        }

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
