package com.hellocuriosity.drappula.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.ui.screens.FeedbackTestTags
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import org.junit.Test
import org.robolectric.annotation.Config
import kotlin.test.assertTrue

@Config(application = MockApplication::class)
class FeedbackHeaderTest : CoroutinesComposeTest() {
    @Test
    fun testFeedbackHeaderDisplaysTitle() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackHeader(onNavigateBack = {})
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.TITLE)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testFeedbackHeaderDisplaysBackButton() {
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackHeader(onNavigateBack = {})
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.BACK_BUTTON)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testFeedbackHeaderBackButtonTriggersOnNavigateBack() {
        var navigatedBack = false
        composeTestRule.setContent {
            DrappulaTheme {
                FeedbackHeader(onNavigateBack = { navigatedBack = true })
            }
        }

        composeTestRule
            .onNodeWithTag(FeedbackTestTags.BACK_BUTTON)
            .performClick()

        assertTrue(navigatedBack)
    }
}
