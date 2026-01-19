package com.hellocuriosity.drappula.models

sealed interface Sound {
    val id: String
    val fileName: String
    val category: Category
}
