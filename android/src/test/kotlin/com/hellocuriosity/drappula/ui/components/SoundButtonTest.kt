package com.hellocuriosity.drappula.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.models.Dracula
import com.hellocuriosity.drappula.ui.screens.SoundPlayerTestTags
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import org.junit.Test
import org.robolectric.annotation.Config

@Config(application = MockApplication::class)
class SoundButtonTest : CoroutinesComposeTest() {
    @Test
    fun testSoundButtonDisplaysName() {
        composeTestRule.setContent {
            DrappulaTheme {
                SoundButton(
                    sound = Dracula.DRACULA,
                    isPlaying = false,
                    onClick = {},
                )
            }
        }

        composeTestRule
            .onNodeWithText(Dracula.DRACULA.displayName)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testSoundButtonIsEnabledWhenNotPlaying() {
        composeTestRule.setContent {
            DrappulaTheme {
                SoundButton(
                    sound = Dracula.DRACULA,
                    isPlaying = false,
                    onClick = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.soundButton(Dracula.DRACULA.id))
            .assertIsEnabled()
    }

    @Test
    fun testSoundButtonIsDisabledWhenPlaying() {
        composeTestRule.setContent {
            DrappulaTheme {
                SoundButton(
                    sound = Dracula.DRACULA,
                    isPlaying = true,
                    onClick = {},
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.soundButton(Dracula.DRACULA.id))
            .assertIsNotEnabled()
    }

    @Test
    fun testSoundButtonClickTriggersOnClick() {
        var clicked = false
        composeTestRule.setContent {
            DrappulaTheme {
                SoundButton(
                    sound = Dracula.DRACULA,
                    isPlaying = false,
                    onClick = { clicked = true },
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.soundButton(Dracula.DRACULA.id))
            .performClick()

        assert(clicked)
    }
}
