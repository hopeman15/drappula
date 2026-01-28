package com.hellocuriosity.drappula.themes

/**
 * Typography configuration for a theme.
 * Platforms implement this with actual font resources.
 */
interface ThemeTypography {
    /**
     * The name of the font family to use (e.g., "Cinzel").
     * Platforms resolve this to their font resources.
     */
    val fontFamily: String
}
