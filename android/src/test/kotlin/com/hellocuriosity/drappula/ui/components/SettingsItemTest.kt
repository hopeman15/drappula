package com.hellocuriosity.drappula.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import org.junit.Test
import org.robolectric.annotation.Config
import kotlin.test.assertTrue

@Config(application = MockApplication::class)
class SettingsItemTest : CoroutinesComposeTest() {
    @Test
    fun testSettingsItemDisplaysTitle() {
        val title = "Test Setting"
        composeTestRule.setContent {
            DrappulaTheme {
                SettingsItem(
                    title = title,
                    onClick = {},
                )
            }
        }

        composeTestRule
            .onNodeWithText(title)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testSettingsItemClickTriggersOnClick() {
        var clicked = false
        val title = "Clickable Setting"
        composeTestRule.setContent {
            DrappulaTheme {
                SettingsItem(
                    title = title,
                    onClick = { clicked = true },
                )
            }
        }

        composeTestRule
            .onNodeWithText(title)
            .performClick()

        assertTrue(clicked)
    }
}
