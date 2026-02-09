package com.hellocuriosity.drappula.models

import kotlin.test.Test
import kotlin.test.assertEquals

class CategoryTest {
    private data class CategoryData(
        val category: Category,
        val title: String,
        val description: String,
        val url: String,
        val license: String,
    )

    private val data =
        listOf(
            CategoryData(
                category = Category.DRACULA,
                title = "Dracula",
                description = "Audio clips sourced from the 1931 Dracula film, which is in the public domain.",
                url = "https://archive.org/details/dracula.-1931",
                license = "Public Domain Mark 1.0",
            ),
        )

    @Test
    fun testCategory() {
        data.forEach { (category, title, description, url, license) ->
            assertEquals(title, category.title)
            assertEquals(description, category.description)
            assertEquals(url, category.url)
            assertEquals(license, category.license)
        }
    }
}
