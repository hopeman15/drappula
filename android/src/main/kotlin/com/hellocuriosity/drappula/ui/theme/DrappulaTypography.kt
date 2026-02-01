package com.hellocuriosity.drappula.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hellocuriosity.drappula.R
import com.hellocuriosity.drappula.themes.DraculaTheme
import com.hellocuriosity.drappula.themes.FontWeight as SharedFontWeight

val CinzelFontFamily =
    FontFamily(
        Font(R.font.cinzel_bold, FontWeight.Bold),
    )

private fun SharedFontWeight.toComposeFontWeight(): FontWeight =
    when (this) {
        SharedFontWeight.NORMAL -> FontWeight.Normal
        SharedFontWeight.MEDIUM -> FontWeight.Medium
        SharedFontWeight.BOLD -> FontWeight.Bold
    }

private val sharedTypography = DraculaTheme.typography

val DrappulaTypography =
    Typography(
        displayLarge =
            TextStyle(
                fontFamily = CinzelFontFamily,
                fontWeight = sharedTypography.display.weight.toComposeFontWeight(),
                fontSize = sharedTypography.display.size.sp,
            ),
        headlineMedium =
            TextStyle(
                fontFamily = CinzelFontFamily,
                fontWeight = sharedTypography.headline.weight.toComposeFontWeight(),
                fontSize = sharedTypography.headline.size.sp,
            ),
        titleMedium =
            TextStyle(
                fontFamily = CinzelFontFamily,
                fontWeight = sharedTypography.title.weight.toComposeFontWeight(),
                fontSize = sharedTypography.title.size.sp,
            ),
        bodyLarge =
            TextStyle(
                fontFamily = CinzelFontFamily,
                fontWeight = sharedTypography.body.weight.toComposeFontWeight(),
                fontSize = sharedTypography.body.size.sp,
            ),
        labelLarge =
            TextStyle(
                fontFamily = CinzelFontFamily,
                fontWeight = sharedTypography.button.weight.toComposeFontWeight(),
                fontSize = sharedTypography.button.size.sp,
            ),
        bodySmall =
            TextStyle(
                fontFamily = CinzelFontFamily,
                fontWeight = sharedTypography.caption.weight.toComposeFontWeight(),
                fontSize = sharedTypography.caption.size.sp,
            ),
    )
