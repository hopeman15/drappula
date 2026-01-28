package com.hellocuriosity.drappula.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hellocuriosity.drappula.R

val CinzelFontFamily =
    FontFamily(
        Font(R.font.cinzel_bold, FontWeight.Bold),
    )

val DrappulaTypography =
    Typography(
        displayLarge =
            TextStyle(
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
            ),
        headlineMedium =
            TextStyle(
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            ),
        titleMedium =
            TextStyle(
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            ),
        bodyLarge =
            TextStyle(
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            ),
        labelLarge =
            TextStyle(
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            ),
        bodySmall =
            TextStyle(
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
            ),
    )
