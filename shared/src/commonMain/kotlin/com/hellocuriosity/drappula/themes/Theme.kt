package com.hellocuriosity.drappula.themes

interface Theme {
    val name: String
    val darkColors: ThemeColor
    val lightColors: ThemeColor
    val typography: Typography
}
