package com.hellocuriosity.drappula.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.provider.CategoryProvider
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import org.junit.Test
import org.robolectric.annotation.Config
import kotlin.test.assertTrue

@Config(application = MockApplication::class)
class AttributionScreenTest : CoroutinesComposeTest() {
    @Test
    fun testAttributionScreenDisplaysCorrectly() {
        composeTestRule.setContent {
            DrappulaTheme {
                AttributionScreen(onNavigateBack = {})
            }
        }

        composeTestRule
            .onNodeWithTag(AttributionTestTags.SCREEN)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(AttributionTestTags.TITLE)
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("Attribution")
    }

    @Test
    fun testBackButtonIsDisplayed() {
        composeTestRule.setContent {
            DrappulaTheme {
                AttributionScreen(onNavigateBack = {})
            }
        }

        composeTestRule
            .onNodeWithTag(AttributionTestTags.BACK_BUTTON)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testBackButtonTriggersCallback() {
        var navigatedBack = false
        composeTestRule.setContent {
            DrappulaTheme {
                AttributionScreen(onNavigateBack = { navigatedBack = true })
            }
        }

        composeTestRule
            .onNodeWithTag(AttributionTestTags.BACK_BUTTON)
            .performClick()

        assertTrue(navigatedBack)
    }

    @Test
    fun testAllAttributionItemsAreDisplayed() {
        composeTestRule.setContent {
            DrappulaTheme {
                AttributionScreen(onNavigateBack = {})
            }
        }

        CategoryProvider.all.forEach { attribution ->
            composeTestRule
                .onNodeWithText(attribution.title)
                .assertExists()
                .assertIsDisplayed()

            composeTestRule
                .onNodeWithText(attribution.license)
                .assertExists()
                .assertIsDisplayed()
        }
    }
}
