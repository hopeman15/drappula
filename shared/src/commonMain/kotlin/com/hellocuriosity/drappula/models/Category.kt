package com.hellocuriosity.drappula.models

enum class Category(
    val title: String,
    val description: String,
    val url: String,
    val license: String,
) {
    DRACULA(
        title = "Dracula",
        description = "Audio clips sourced from the 1931 Dracula film, which is in the public domain.",
        url = "https://archive.org/details/dra-cula.-eng.-mp-4",
        license = "Public Domain Mark 1.0",
    ),
}
