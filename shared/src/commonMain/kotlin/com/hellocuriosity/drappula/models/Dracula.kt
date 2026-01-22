package com.hellocuriosity.drappula.models

enum class Dracula(
    override val id: String,
    override val displayName: String,
    override val fileName: String,
    override val category: Category = Category.DRACULA,
) : Sound {
    I_AM(id = "i_am", displayName = "I Am", fileName = "01_i_am.mp3"),
    DRACULA(id = "dracula", displayName = "Dracula", fileName = "02_dracula.mp3"),
}
