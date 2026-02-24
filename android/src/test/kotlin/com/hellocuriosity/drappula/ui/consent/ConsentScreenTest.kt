package com.hellocuriosity.drappula.ui.consent

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import com.hellocuriosity.drappula.consent.ConsentState
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class ConsentScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testConsentScreenDisplaysTitle() {
        composeTestRule.setContent {
            DrappulaTheme {
                ConsentScreen(onConsentGiven = {})
            }
        }
        composeTestRule.onNodeWithText("Your Privacy").assertIsDisplayed()
    }

    @Test
    fun testConsentScreenDisplaysAllButtons() {
        composeTestRule.setContent {
            DrappulaTheme {
                ConsentScreen(onConsentGiven = {})
            }
        }
        composeTestRule.onNodeWithText("Accept All").assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Continue with Selected")
            .performScrollTo()
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("Decline All")
            .performScrollTo()
            .assertIsDisplayed()
    }

    @Test
    fun testAcceptAllCallsCallbackWithAllEnabled() {
        var receivedState: ConsentState? = null
        composeTestRule.setContent {
            DrappulaTheme {
                ConsentScreen(onConsentGiven = { receivedState = it })
            }
        }
        composeTestRule.onNodeWithText("Accept All").performClick()
        assertNotNull(receivedState)
        assertTrue(receivedState!!.analytics)
        assertTrue(receivedState!!.crashReporting)
    }

    @Test
    fun testDeclineAllCallsCallbackWithAllDisabled() {
        var receivedState: ConsentState? = null
        composeTestRule.setContent {
            DrappulaTheme {
                ConsentScreen(onConsentGiven = { receivedState = it })
            }
        }
        composeTestRule
            .onNodeWithText("Decline All")
            .performScrollTo()
            .performClick()
        assertNotNull(receivedState)
        assertFalse(receivedState!!.analytics)
        assertFalse(receivedState!!.crashReporting)
    }
}
