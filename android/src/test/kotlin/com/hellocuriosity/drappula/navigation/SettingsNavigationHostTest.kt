package com.hellocuriosity.drappula.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.ui.screens.AttributionTestTags
import com.hellocuriosity.drappula.ui.screens.SettingsTestTags
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import org.junit.Test
import org.robolectric.annotation.Config

@Config(application = MockApplication::class)
class SettingsNavigationHostTest : CoroutinesComposeTest() {
    @Test
    fun testInitialStateShowsSettingsScreen() {
        composeTestRule.setContent {
            DrappulaTheme {
                SettingsNavigationHost()
            }
        }

        composeTestRule
            .onNodeWithTag(SettingsTestTags.SCREEN)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testNavigateToAttributionScreen() {
        composeTestRule.setContent {
            DrappulaTheme {
                SettingsNavigationHost()
            }
        }

        composeTestRule
            .onNodeWithTag(SettingsTestTags.ATTRIBUTION_ITEM)
            .performClick()

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithTag(AttributionTestTags.SCREEN)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testNavigateBackFromAttributionScreen() {
        composeTestRule.setContent {
            DrappulaTheme {
                SettingsNavigationHost()
            }
        }

        // Navigate to Attribution screen
        composeTestRule
            .onNodeWithTag(SettingsTestTags.ATTRIBUTION_ITEM)
            .performClick()

        composeTestRule.waitForIdle()

        // Navigate back
        composeTestRule
            .onNodeWithTag(AttributionTestTags.BACK_BUTTON)
            .performClick()

        composeTestRule.waitForIdle()

        // Verify we're back on Settings screen
        composeTestRule
            .onNodeWithTag(SettingsTestTags.SCREEN)
            .assertExists()
            .assertIsDisplayed()
    }
}
