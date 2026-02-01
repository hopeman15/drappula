package com.hellocuriosity.drappula.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import com.hellocuriosity.drappula.themes.DraculaTheme
import com.hellocuriosity.drappula.themes.ThemeColor

@Composable
fun DrappulaTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val themeColors = if (isDarkTheme) DraculaTheme.darkColors else DraculaTheme.lightColors
    val colorScheme = themeColors.toColorScheme(isDarkTheme)

    CompositionLocalProvider(
        LocalThemeColors provides themeColors,
        LocalBackgroundGradient provides themeColors.backgroundGradient(),
        LocalButtonGradient provides themeColors.buttonGradient(),
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
 * Local provider for button gradient.
 */
val LocalButtonGradient =
    staticCompositionLocalOf<Brush> {
        error("No ButtonGradient provided")
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

    val buttonGradient: Brush
        @Composable
        get() = LocalButtonGradient.current
}
