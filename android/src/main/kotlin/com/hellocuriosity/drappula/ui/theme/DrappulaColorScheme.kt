package com.hellocuriosity.drappula.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Brush
import com.hellocuriosity.drappula.themes.ThemeColor

fun ThemeColor.toColorScheme(isDark: Boolean): ColorScheme =
    if (isDark) {
        darkColorScheme(
            primary = primary.toColor(),
            secondary = secondary.toColor(),
            background = background.toColor(),
            surface = surface.toColor(),
            onPrimary = onPrimary.toColor(),
            onBackground = onBackground.toColor(),
            onSurface = onSurface.toColor(),
        )
    } else {
        lightColorScheme(
            primary = primary.toColor(),
            secondary = secondary.toColor(),
            background = background.toColor(),
            surface = surface.toColor(),
            onPrimary = onPrimary.toColor(),
            onBackground = onBackground.toColor(),
            onSurface = onSurface.toColor(),
        )
    }

fun ThemeColor.backgroundGradient(): Brush =
    Brush.verticalGradient(
        colors = listOf(gradientStart.toColor(), gradientEnd.toColor()),
    )

fun ThemeColor.buttonGradient(): Brush =
    Brush.verticalGradient(
        colors = listOf(gradientStart.toColor(), gradientEnd.toColor()),
    )
