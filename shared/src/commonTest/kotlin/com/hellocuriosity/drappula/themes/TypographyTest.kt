package com.hellocuriosity.drappula.themes

import kotlin.test.Test
import kotlin.test.assertEquals

class TypographyTest {
    private val typography = Typography.Default

    @Test
    fun testDefaultFontFamily() {
        assertEquals("Cinzel", typography.fontFamily)
    }

    @Test
    fun testDefaultDisplay() {
        assertEquals(32, typography.display.size)
        assertEquals(FontWeight.BOLD, typography.display.weight)
    }

    @Test
    fun testDefaultHeadline() {
        assertEquals(24, typography.headline.size)
        assertEquals(FontWeight.BOLD, typography.headline.weight)
    }

    @Test
    fun testDefaultTitle() {
        assertEquals(20, typography.title.size)
        assertEquals(FontWeight.BOLD, typography.title.weight)
    }

    @Test
    fun testDefaultBody() {
        assertEquals(16, typography.body.size)
        assertEquals(FontWeight.NORMAL, typography.body.weight)
    }

    @Test
    fun testDefaultButton() {
        assertEquals(16, typography.button.size)
        assertEquals(FontWeight.MEDIUM, typography.button.weight)
    }

    @Test
    fun testDefaultCaption() {
        assertEquals(12, typography.caption.size)
        assertEquals(FontWeight.NORMAL, typography.caption.weight)
    }
}
