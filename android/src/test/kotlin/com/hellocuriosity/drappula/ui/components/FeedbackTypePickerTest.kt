package com.hellocuriosity.drappula.ui.components

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.models.Feedback
import com.hellocuriosity.drappula.ui.screens.FeedbackTestTags
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import org.junit.Test
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@Config(application = MockApplication::class)
class FeedbackTypePickerTest : CoroutinesComposeTest() {
    @Test
    fun testFeedbackTypePickerIsDisplayed() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackTypePicker(
                    feedback = Feedback(),
                    onUpdateFeedback = {},
                    colors = OutlinedTextFieldDefaults.colors(),
                )
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.TYPE_PICKER)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testFeedbackTypePickerDisplaysDefaultType() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackTypePicker(
                    feedback = Feedback(),
                    onUpdateFeedback = {},
                    colors = OutlinedTextFieldDefaults.colors(),
                )
            }
        }

        composeTestRule
            .onNodeWithText("Enhancement")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testFeedbackTypePickerDisplaysSelectedType() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackTypePicker(
                    feedback = Feedback(type = Feedback.Type.FIX),
                    onUpdateFeedback = {},
                    colors = OutlinedTextFieldDefaults.colors(),
                )
            }
        }

        composeTestRule
            .onNodeWithText("Fix")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testFeedbackTypePickerShowsDropdownOnClick() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackTypePicker(
                    feedback = Feedback(),
                    onUpdateFeedback = {},
                    colors = OutlinedTextFieldDefaults.colors(),
                )
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.TYPE_PICKER)
            .performClick()

        composeTestRule
            .onNodeWithText("Feature Request")
            .assertIsDisplayed()
    }

    @Test
    fun testFeedbackTypePickerSelectsType() {
        var updatedFeedback: Feedback? = null
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackTypePicker(
                    feedback = Feedback(),
                    onUpdateFeedback = { updatedFeedback = it },
                    colors = OutlinedTextFieldDefaults.colors(),
                )
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.TYPE_PICKER)
            .performClick()

        composeTestRule
            .onNodeWithText("Feature Request")
            .performClick()

        assertEquals(Feedback.Type.FEATURE, updatedFeedback?.type)
    }
}
