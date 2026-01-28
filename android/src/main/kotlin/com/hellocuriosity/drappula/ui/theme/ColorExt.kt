package com.hellocuriosity.drappula.ui.theme

import androidx.compose.ui.graphics.Color

@Suppress("MagicNumber")
fun String.toColor(): Color = Color(("FF$this").toLong(radix = 16))
