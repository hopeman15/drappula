package com.hellocuriosity.drappula.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Brush
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.themes.ThemeColor
import org.junit.Test
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import com.hellocuriosity.drappula.themes.DraculaTheme as SharedDraculaTheme

@Config(application = MockApplication::class)
class DrappulaThemeTest : CoroutinesComposeTest() {
    @Test
    fun testDarkThemeProvidesCorrectColors() {
        var themeColors: ThemeColor? = null

        composeTestRule.setContent {
            DrappulaTheme(isDarkTheme = true) {
                themeColors = DrappulaTheme.colors
            }
        }

        assertEquals(SharedDraculaTheme.darkColors, themeColors)
    }

    @Test
    fun testLightThemeProvidesCorrectColors() {
        var themeColors: ThemeColor? = null

        composeTestRule.setContent {
            DrappulaTheme(isDarkTheme = false) {
                themeColors = DrappulaTheme.colors
            }
        }

        assertEquals(SharedDraculaTheme.lightColors, themeColors)
    }

    @Test
    fun testThemeProvidesBackgroundGradient() {
        var backgroundGradient: Brush? = null

        composeTestRule.setContent {
            DrappulaTheme(isDarkTheme = true) {
                backgroundGradient = DrappulaTheme.backgroundGradient
            }
        }

        assertEquals(SharedDraculaTheme.darkColors.backgroundGradient(), backgroundGradient)
    }

    @Test
    fun testMaterialThemeHasCorrectTypography() {
        var typography: androidx.compose.material3.Typography? = null

        composeTestRule.setContent {
            DrappulaTheme {
                typography = MaterialTheme.typography
            }
        }

        assertEquals(DrappulaTypography, typography)
    }

    @Test
    fun testMaterialThemeHasCorrectColorScheme() {
        var primaryColor: androidx.compose.ui.graphics.Color? = null

        composeTestRule.setContent {
            DrappulaTheme(isDarkTheme = true) {
                primaryColor = MaterialTheme.colorScheme.primary
            }
        }

        assertEquals(SharedDraculaTheme.darkColors.primary.toColor(), primaryColor)
    }
}
