package com.hellocuriosity.drappula.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.ui.feedback.FeedbackViewModel
import com.hellocuriosity.drappula.ui.screens.AttributionTestTags
import com.hellocuriosity.drappula.ui.screens.FeedbackTestTags
import com.hellocuriosity.drappula.ui.screens.SettingsTestTags
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import io.mockk.mockk
import org.junit.Test
import org.robolectric.annotation.Config

@Config(application = MockApplication::class)
class SettingsNavigationHostTest : CoroutinesComposeTest() {
    private val feedbackViewModel: FeedbackViewModel = mockk(relaxed = true)

    @Test
    fun testInitialStateShowsSettingsScreen() {
        composeTestRule.setContent {
            DrappulaTheme {
                SettingsNavigationHost(feedbackViewModel = feedbackViewModel)
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
                SettingsNavigationHost(feedbackViewModel = feedbackViewModel)
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
                SettingsNavigationHost(feedbackViewModel = feedbackViewModel)
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

    @Test
    fun testNavigateToFeedbackScreen() {
        composeTestRule.setContent {
            DrappulaTheme {
                SettingsNavigationHost(feedbackViewModel = feedbackViewModel)
            }
        }

        composeTestRule
            .onNodeWithTag(SettingsTestTags.FEEDBACK_ITEM)
            .performClick()

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.SCREEN)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testNavigateBackFromFeedbackScreen() {
        composeTestRule.setContent {
            DrappulaTheme {
                SettingsNavigationHost(feedbackViewModel = feedbackViewModel)
            }
        }

        // Navigate to Feedback screen
        composeTestRule
            .onNodeWithTag(SettingsTestTags.FEEDBACK_ITEM)
            .performClick()

        composeTestRule.waitForIdle()

        // Navigate back
        composeTestRule
            .onNodeWithTag(FeedbackTestTags.BACK_BUTTON)
            .performClick()

        composeTestRule.waitForIdle()

        // Verify we're back on Settings screen
        composeTestRule
            .onNodeWithTag(SettingsTestTags.SCREEN)
            .assertExists()
            .assertIsDisplayed()
    }
}
