package com.hellocuriosity.drappula.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import com.hellocuriosity.drappula.themes.Theme
import com.hellocuriosity.drappula.themes.ThemeColor
import com.hellocuriosity.drappula.themes.DraculaTheme as SharedDraculaTheme

@Composable
fun DrappulaTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    theme: Theme = SharedDraculaTheme,
    content: @Composable () -> Unit,
) {
    val themeColors = if (isDarkTheme) theme.darkColors else theme.lightColors
    val colorScheme = themeColors.toColorScheme(isDarkTheme)

    CompositionLocalProvider(
        LocalThemeColors provides themeColors,
        LocalBackgroundGradient provides themeColors.backgroundGradient(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = DrappulaTypography,
            content = content,
        )
    }
}

/**
 * Local provider for theme colors (includes gradients not in MaterialTheme).
 */
val LocalThemeColors =
    staticCompositionLocalOf<ThemeColor> {
        error("No ThemeColors provided")
    }

/**
 * Local provider for background gradient.
 */
val LocalBackgroundGradient =
    staticCompositionLocalOf<Brush> {
        error("No BackgroundGradient provided")
    }

/**
 * Convenience object for accessing Drappula-specific theme values.
 */
object DrappulaTheme {
    val colors: ThemeColor
        @Composable
        get() = LocalThemeColors.current

    val backgroundGradient: Brush
        @Composable
        get() = LocalBackgroundGradient.current
}
