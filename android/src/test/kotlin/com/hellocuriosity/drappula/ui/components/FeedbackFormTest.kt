package com.hellocuriosity.drappula.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithTag
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.models.Feedback
import com.hellocuriosity.drappula.ui.screens.FeedbackTestTags
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import org.junit.Test
import org.robolectric.annotation.Config

@Config(application = MockApplication::class)
class FeedbackFormTest : CoroutinesComposeTest() {
    @Test
    fun testFeedbackFormDisplaysTypePicker() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackForm(
                    feedback = Feedback(),
                    isSubmitEnabled = false,
                    onUpdateFeedback = {},
                    onSubmit = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.TYPE_PICKER)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testFeedbackFormDisplaysTitleField() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackForm(
                    feedback = Feedback(),
                    isSubmitEnabled = false,
                    onUpdateFeedback = {},
                    onSubmit = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.TITLE_FIELD)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testFeedbackFormDisplaysMessageField() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackForm(
                    feedback = Feedback(),
                    isSubmitEnabled = false,
                    onUpdateFeedback = {},
                    onSubmit = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.MESSAGE_FIELD)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testFeedbackFormSubmitButtonEnabledWhenSubmitEnabled() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackForm(
                    feedback = Feedback(title = "Title", message = "Message"),
                    isSubmitEnabled = true,
                    onUpdateFeedback = {},
                    onSubmit = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.SUBMIT_BUTTON)
            .assertIsEnabled()
    }

    @Test
    fun testFeedbackFormSubmitButtonDisabledWhenSubmitNotEnabled() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackForm(
                    feedback = Feedback(),
                    isSubmitEnabled = false,
                    onUpdateFeedback = {},
                    onSubmit = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.SUBMIT_BUTTON)
            .assertIsNotEnabled()
    }
}
