package com.hellocuriosity.drappula.provider

import com.hellocuriosity.drappula.models.Category
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CategoryProviderTest {
    private val categories = listOf(Category.DRACULA)

    @Test
    fun testAll() {
        categories.forEach { category ->
            assertTrue(CategoryProvider.all.contains(category))
        }
    }

    @Test
    fun testAllSize() {
        assertEquals(1, CategoryProvider.all.size)
    }
}
