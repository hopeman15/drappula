package com.hellocuriosity.drappula.ui.theme

import androidx.compose.ui.graphics.Color
import org.junit.Test
import kotlin.test.assertEquals

class ColorExtTest {
    @Test
    fun testToColorConvertsHexString() {
        assertEquals(Color(0xFFFF5733), "ff5733".toColor())
    }

    @Test
    fun testToColorHandlesUppercaseHex() {
        assertEquals(Color(0xFFABCDEF), "ABCDEF".toColor())
    }

    @Test
    fun testToColorHandlesLowercaseHex() {
        assertEquals(Color(0xFFABCDEF), "abcdef".toColor())
    }

    @Test
    fun testToColorHandlesBlack() {
        assertEquals(Color(0xFF000000), "000000".toColor())
    }

    @Test
    fun testToColorHandlesWhite() {
        assertEquals(Color(0xFFFFFFFF), "ffffff".toColor())
    }

    @Test
    fun testToColorHandlesRed() {
        assertEquals(Color(0xFFFF0000), "ff0000".toColor())
    }

    @Test
    fun testToColorHandlesGreen() {
        assertEquals(Color(0xFF00FF00), "00ff00".toColor())
    }

    @Test
    fun testToColorHandlesBlue() {
        assertEquals(Color(0xFF0000FF), "0000ff".toColor())
    }

    @Test
    fun testToColorSetsFullAlpha() {
        val color = "123456".toColor()
        assertEquals(1f, color.alpha)
    }
}
