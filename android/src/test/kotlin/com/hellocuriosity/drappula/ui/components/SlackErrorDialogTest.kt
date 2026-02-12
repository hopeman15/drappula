package com.hellocuriosity.drappula.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import org.junit.Test
import org.robolectric.annotation.Config
import kotlin.test.assertTrue

@Config(application = MockApplication::class)
class SlackErrorDialogTest : CoroutinesComposeTest() {
    @Test
    fun testSlackErrorDialogIsDisplayed() {
        composeTestRule.setContent {
            DrappulaTheme {
                SlackErrorDialog(
                    onCancel = {},
                    onRetry = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SlackErrorDialogTestTags.DIALOG)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testSlackErrorDialogDisplaysMessage() {
        composeTestRule.setContent {
            DrappulaTheme {
                SlackErrorDialog(
                    onCancel = {},
                    onRetry = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SlackErrorDialogTestTags.MESSAGE)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testSlackErrorDialogCancelTriggersOnCancel() {
        var cancelled = false
        composeTestRule.setContent {
            DrappulaTheme {
                SlackErrorDialog(
                    onCancel = { cancelled = true },
                    onRetry = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SlackErrorDialogTestTags.CANCEL_BUTTON)
            .performClick()

        assertTrue(cancelled)
    }

    @Test
    fun testSlackErrorDialogRetryTriggersOnRetry() {
        var retried = false
        composeTestRule.setContent {
            DrappulaTheme {
                SlackErrorDialog(
                    onCancel = {},
                    onRetry = { retried = true },
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SlackErrorDialogTestTags.RETRY_BUTTON)
            .performClick()

        assertTrue(retried)
    }
}
