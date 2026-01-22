package com.hellocuriosity.drappula.coroutines

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
abstract class CoroutinesComposeTest : CoroutinesTestCase() {
    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()
}
