package com.hellocuriosity.drappula.models

import kotlin.test.Test
import kotlin.test.assertEquals

class CategoryTest {
    private val data =
        listOf(
            Pair("Dracula", Category.DRACULA),
        )

    @Test
    fun testCategory() {
        data.forEach { (expected, category) ->
            assertEquals(expected, category.displayName)
        }
    }
}
