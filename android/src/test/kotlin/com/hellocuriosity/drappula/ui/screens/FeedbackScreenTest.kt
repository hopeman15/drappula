package com.hellocuriosity.drappula.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.models.Feedback
import com.hellocuriosity.drappula.ui.components.SlackErrorDialogTestTags
import com.hellocuriosity.drappula.ui.feedback.FeedbackViewModel
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test
import org.robolectric.annotation.Config
import kotlin.test.assertTrue

@Config(application = MockApplication::class)
class FeedbackScreenTest : CoroutinesComposeTest() {
    private val state = MutableStateFlow(FeedbackViewModel.State())
    private val viewModel: FeedbackViewModel =
        mockk(relaxed = true) {
            every { this@mockk.state } returns this@FeedbackScreenTest.state
        }

    @Test
    fun testFeedbackScreenDisplaysCorrectly() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackScreen(viewModel = viewModel, onNavigateBack = {})
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.SCREEN)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testFeedbackHeaderIsDisplayed() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackScreen(viewModel = viewModel, onNavigateBack = {})
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.BACK_BUTTON, useUnmergedTree = true)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.TITLE, useUnmergedTree = true)
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("Feedback")
    }

    @Test
    fun testFeedbackFormFieldsAreDisplayed() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackScreen(viewModel = viewModel, onNavigateBack = {})
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.TYPE_PICKER, useUnmergedTree = true)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.TITLE_FIELD, useUnmergedTree = true)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.MESSAGE_FIELD, useUnmergedTree = true)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.SUBMIT_BUTTON, useUnmergedTree = true)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testBackButtonTriggersNavigation() {
        var navigatedBack = false
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackScreen(viewModel = viewModel, onNavigateBack = { navigatedBack = true })
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.BACK_BUTTON)
            .performClick()

        assertTrue(navigatedBack)
    }

    @Test
    fun testErrorDialogIsDisplayedWhenStateHasError() {
        state.value = FeedbackViewModel.State(error = RuntimeException("test error"))

        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackScreen(viewModel = viewModel, onNavigateBack = {})
            }
        }

        composeTestRule
            .onNodeWithTag(SlackErrorDialogTestTags.DIALOG)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testErrorDialogCancelClearsError() {
        state.value = FeedbackViewModel.State(error = RuntimeException("test error"))

        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackScreen(viewModel = viewModel, onNavigateBack = {})
            }
        }

        composeTestRule
            .onNodeWithTag(SlackErrorDialogTestTags.CANCEL_BUTTON)
            .performClick()

        verify { viewModel.clearError() }
    }

    @Test
    fun testNoErrorDialogWhenStateHasNoError() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackScreen(viewModel = viewModel, onNavigateBack = {})
            }
        }

        composeTestRule
            .onNodeWithTag(SlackErrorDialogTestTags.DIALOG)
            .assertDoesNotExist()
    }

    @Test
    fun testSubmitButtonClickCallsSubmit() {
        state.value =
            FeedbackViewModel.State(
                feedback = Feedback(title = "Test", message = "Test message"),
            )

        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackScreen(viewModel = viewModel, onNavigateBack = {})
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.SUBMIT_BUTTON)
            .performClick()

        verify { viewModel.submit(any()) }
    }
}
