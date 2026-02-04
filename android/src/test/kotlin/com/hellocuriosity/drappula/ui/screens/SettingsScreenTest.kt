package com.hellocuriosity.drappula.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import org.junit.Test
import org.robolectric.annotation.Config
import kotlin.test.assertTrue

@Config(application = MockApplication::class)
class SettingsScreenTest : CoroutinesComposeTest() {
    @Test
    fun testSettingsScreenDisplaysCorrectly() {
        composeTestRule.setContent {
            DrappulaTheme {
                SettingsScreen()
            }
        }

        composeTestRule
            .onNodeWithTag(SettingsTestTags.SCREEN)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(SettingsTestTags.TITLE)
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("Settings")
    }

    @Test
    fun testAttributionItemIsDisplayed() {
        composeTestRule.setContent {
            DrappulaTheme {
                SettingsScreen()
            }
        }

        composeTestRule
            .onNodeWithTag(SettingsTestTags.ATTRIBUTION_ITEM)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testAttributionItemClickTriggersCallback() {
        var navigatedToAttribution = false
        composeTestRule.setContent {
            DrappulaTheme {
                SettingsScreen(onNavigateToAttribution = { navigatedToAttribution = true })
            }
        }

        composeTestRule
            .onNodeWithTag(SettingsTestTags.ATTRIBUTION_ITEM)
            .performClick()

        assertTrue(navigatedToAttribution)
    }
}
