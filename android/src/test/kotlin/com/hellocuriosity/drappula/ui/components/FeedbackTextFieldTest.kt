package com.hellocuriosity.drappula.ui.components

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import org.junit.Test
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@Config(application = MockApplication::class)
class FeedbackTextFieldTest : CoroutinesComposeTest() {
    @Test
    fun testFeedbackTextFieldDisplaysLabel() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackTextField(
                    value = "",
                    onValueChange = {},
                    label = "Title",
                    counter = "0/30",
                    colors = OutlinedTextFieldDefaults.colors(),
                )
            }
        }

        composeTestRule
            .onNodeWithText("Title")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testFeedbackTextFieldDisplaysCounter() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackTextField(
                    value = "",
                    onValueChange = {},
                    label = "Title",
                    counter = "0/30",
                    colors = OutlinedTextFieldDefaults.colors(),
                )
            }
        }

        composeTestRule
            .onNodeWithText("0/30")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testFeedbackTextFieldDisplaysValue() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackTextField(
                    value = "Hello",
                    onValueChange = {},
                    label = "Title",
                    counter = "5/30",
                    colors = OutlinedTextFieldDefaults.colors(),
                )
            }
        }

        composeTestRule
            .onNodeWithText("Hello")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testFeedbackTextFieldTriggersOnValueChange() {
        var updatedValue = ""
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackTextField(
                    value = "",
                    onValueChange = { updatedValue = it },
                    label = "Title",
                    counter = "0/30",
                    colors = OutlinedTextFieldDefaults.colors(),
                )
            }
        }

        composeTestRule
            .onNodeWithText("Title")
            .performTextInput("Test")

        assertEquals("Test", updatedValue)
    }
}
