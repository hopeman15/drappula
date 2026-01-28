package com.hellocuriosity.drappula.themes

/**
 * Typography configuration for a theme.
 * Defines font family and text style configurations that platforms
 * use to construct their native typography.
 */
interface Typography {
    val fontFamily: String
    val display: TextStyleConfig
    val headline: TextStyleConfig
    val title: TextStyleConfig
    val body: TextStyleConfig
    val button: TextStyleConfig
    val caption: TextStyleConfig
}
