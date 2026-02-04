package com.hellocuriosity.drappula.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.models.Category
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import org.junit.Test
import org.robolectric.annotation.Config
import kotlin.test.assertTrue

@Config(application = MockApplication::class)
class AttributionItemTest : CoroutinesComposeTest() {
    @Test
    fun testAttributionItemDisplaysTitle() {
        composeTestRule.setContent {
            DrappulaTheme {
                AttributionItem(
                    attribution = Category.DRACULA,
                    onClick = {},
                )
            }
        }

        composeTestRule
            .onNodeWithText(Category.DRACULA.title)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testAttributionItemDisplaysLicense() {
        composeTestRule.setContent {
            DrappulaTheme {
                AttributionItem(
                    attribution = Category.DRACULA,
                    onClick = {},
                )
            }
        }

        composeTestRule
            .onNodeWithText(Category.DRACULA.license)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testAttributionItemClickTriggersOnClick() {
        var clicked = false
        composeTestRule.setContent {
            DrappulaTheme {
                AttributionItem(
                    attribution = Category.DRACULA,
                    onClick = { clicked = true },
                )
            }
        }

        composeTestRule
            .onNodeWithText(Category.DRACULA.title)
            .performClick()

        assertTrue(clicked)
    }
}
