package com.hellocuriosity.drappula.ui.theme

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.themes.DraculaTheme
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(application = MockApplication::class)
class DrappulaTypographyTest {
    private val sharedTypography = DraculaTheme.typography

    @Test
    fun testDisplayLarge() {
        assertEquals(sharedTypography.display.size.sp, DrappulaTypography.displayLarge.fontSize)
        assertEquals(FontWeight.Bold, DrappulaTypography.displayLarge.fontWeight)
        assertEquals(CinzelFontFamily, DrappulaTypography.displayLarge.fontFamily)
    }

    @Test
    fun testHeadlineMedium() {
        assertEquals(sharedTypography.headline.size.sp, DrappulaTypography.headlineMedium.fontSize)
        assertEquals(FontWeight.Bold, DrappulaTypography.headlineMedium.fontWeight)
        assertEquals(CinzelFontFamily, DrappulaTypography.headlineMedium.fontFamily)
    }

    @Test
    fun testTitleMedium() {
        assertEquals(sharedTypography.title.size.sp, DrappulaTypography.titleMedium.fontSize)
        assertEquals(FontWeight.Bold, DrappulaTypography.titleMedium.fontWeight)
        assertEquals(CinzelFontFamily, DrappulaTypography.titleMedium.fontFamily)
    }

    @Test
    fun testBodyLarge() {
        assertEquals(sharedTypography.body.size.sp, DrappulaTypography.bodyLarge.fontSize)
        assertEquals(FontWeight.Normal, DrappulaTypography.bodyLarge.fontWeight)
        assertEquals(CinzelFontFamily, DrappulaTypography.bodyLarge.fontFamily)
    }

    @Test
    fun testLabelLarge() {
        assertEquals(sharedTypography.button.size.sp, DrappulaTypography.labelLarge.fontSize)
        assertEquals(FontWeight.Medium, DrappulaTypography.labelLarge.fontWeight)
        assertEquals(CinzelFontFamily, DrappulaTypography.labelLarge.fontFamily)
    }

    @Test
    fun testBodySmall() {
        assertEquals(sharedTypography.caption.size.sp, DrappulaTypography.bodySmall.fontSize)
        assertEquals(FontWeight.Normal, DrappulaTypography.bodySmall.fontWeight)
        assertEquals(CinzelFontFamily, DrappulaTypography.bodySmall.fontFamily)
    }
}
