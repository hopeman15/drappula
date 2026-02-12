package com.hellocuriosity.drappula.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.ui.screens.FeedbackTestTags
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import org.junit.Test
import org.robolectric.annotation.Config
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@Config(application = MockApplication::class)
class FeedbackSubmitButtonTest : CoroutinesComposeTest() {
    @Test
    fun testFeedbackSubmitButtonIsDisplayed() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackSubmitButton(
                    enabled = true,
                    onClick = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.SUBMIT_BUTTON)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testFeedbackSubmitButtonIsEnabledWhenEnabled() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackSubmitButton(
                    enabled = true,
                    onClick = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.SUBMIT_BUTTON)
            .assertIsEnabled()
    }

    @Test
    fun testFeedbackSubmitButtonIsDisabledWhenNotEnabled() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackSubmitButton(
                    enabled = false,
                    onClick = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.SUBMIT_BUTTON)
            .assertIsNotEnabled()
    }

    @Test
    fun testFeedbackSubmitButtonClickTriggersOnClick() {
        var clicked = false
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackSubmitButton(
                    enabled = true,
                    onClick = { clicked = true },
                )
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.SUBMIT_BUTTON)
            .performClick()

        assertTrue(clicked)
    }

    @Test
    fun testFeedbackSubmitButtonClickDoesNotTriggerWhenDisabled() {
        var clicked = false
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackSubmitButton(
                    enabled = false,
                    onClick = { clicked = true },
                )
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.SUBMIT_BUTTON)
            .performClick()

        assertFalse(clicked)
    }
}
