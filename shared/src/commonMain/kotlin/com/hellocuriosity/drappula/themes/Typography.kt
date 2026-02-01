package com.hellocuriosity.drappula.themes

data class Typography(
    val fontFamily: String,
    val display: TextStyleConfig,
    val headline: TextStyleConfig,
    val title: TextStyleConfig,
    val body: TextStyleConfig,
    val button: TextStyleConfig,
    val caption: TextStyleConfig,
) {
    companion object {
        val Default: Typography =
            Typography(
                fontFamily = "Cinzel",
                display = TextStyleConfig(size = 32, weight = FontWeight.BOLD),
                headline = TextStyleConfig(size = 24, weight = FontWeight.BOLD),
                title = TextStyleConfig(size = 20, weight = FontWeight.BOLD),
                body = TextStyleConfig(size = 16, weight = FontWeight.NORMAL),
                button = TextStyleConfig(size = 16, weight = FontWeight.MEDIUM),
                caption = TextStyleConfig(size = 12, weight = FontWeight.NORMAL),
            )
    }
}
