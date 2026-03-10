package com.hellocuriosity.drappula.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.models.Dracula
import com.hellocuriosity.drappula.models.SoundSequence
import com.hellocuriosity.drappula.ui.screens.SoundPlayerTestTags
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import org.junit.Test
import org.robolectric.annotation.Config
import kotlin.test.assertTrue

@Config(application = MockApplication::class)
class SequenceButtonTest : CoroutinesComposeTest() {
    private val sequence =
        SoundSequence(
            id = 1L,
            name = "I am Dracula",
            sounds = listOf(Dracula.I, Dracula.AM, Dracula.DRACULA),
        )

    @Test
    fun testSequenceButtonDisplaysName() {
        composeTestRule.setContent {
            DrappulaTheme {
                SequenceButton(
                    sequence = sequence,
                    isPlaying = false,
                    onClick = {},
                    onLongClick = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.sequenceButton(1L))
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("I am Dracula")
    }

    @Test
    fun testSequenceButtonIsEnabledWhenNotPlaying() {
        composeTestRule.setContent {
            DrappulaTheme {
                SequenceButton(
                    sequence = sequence,
                    isPlaying = false,
                    onClick = {},
                    onLongClick = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.sequenceButton(1L))
            .assertIsEnabled()
    }

    @Test
    fun testSequenceButtonIsDisabledWhenPlaying() {
        composeTestRule.setContent {
            DrappulaTheme {
                SequenceButton(
                    sequence = sequence,
                    isPlaying = true,
                    onClick = {},
                    onLongClick = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.sequenceButton(1L))
            .assertIsNotEnabled()
    }

    @Test
    fun testSequenceButtonClickTriggersOnClick() {
        var clicked = false
        composeTestRule.setContent {
            DrappulaTheme {
                SequenceButton(
                    sequence = sequence,
                    isPlaying = false,
                    onClick = { clicked = true },
                    onLongClick = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.sequenceButton(1L))
            .performClick()

        assertTrue(clicked)
    }
}
