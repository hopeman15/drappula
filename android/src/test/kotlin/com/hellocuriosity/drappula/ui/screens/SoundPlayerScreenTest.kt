package com.hellocuriosity.drappula.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.SoundPlayer
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.models.Category
import com.hellocuriosity.drappula.models.Dracula
import com.hellocuriosity.drappula.ui.soundplayer.DefaultSoundPlayerViewModel
import com.hellocuriosity.drappula.ui.soundplayer.SoundPlayerViewModel
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.robolectric.annotation.Config

@Config(application = MockApplication::class)
class SoundPlayerScreenTest : CoroutinesComposeTest() {
    private val soundPlayer: SoundPlayer = mockk(relaxed = true)

    private val viewModel: SoundPlayerViewModel by lazy {
        DefaultSoundPlayerViewModel(
            soundPlayer = soundPlayer,
            dispatchers = dispatchers,
        )
    }

    @Test
    fun testSoundPlayerScreenDisplaysCorrectly() {
        composeTestRule.setContent {
            SoundPlayerScreen(
                category = Category.DRACULA,
                viewModel = viewModel,
            )
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.SCREEN)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.TITLE)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Drappula")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.FLOW_ROW)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testAllSoundButtonsAreDisplayed() {
        composeTestRule.setContent {
            SoundPlayerScreen(
                category = Category.DRACULA,
                viewModel = viewModel,
            )
        }

        Dracula.entries.forEach { sound ->
            composeTestRule
                .onNodeWithTag(SoundPlayerTestTags.soundButton(sound.id))
                .assertExists()
                .assertIsDisplayed()

            composeTestRule
                .onNodeWithText(sound.displayName)
                .assertExists()
                .assertIsDisplayed()
        }
    }

    @Test
    fun testButtonsAreEnabledInitially() {
        composeTestRule.setContent {
            SoundPlayerScreen(
                category = Category.DRACULA,
                viewModel = viewModel,
            )
        }

        Dracula.entries.forEach { sound ->
            composeTestRule
                .onNodeWithTag(SoundPlayerTestTags.soundButton(sound.id))
                .assertIsEnabled()
        }
    }

    @Test
    fun testButtonClickPlaysSoundForIAm() {
        composeTestRule.setContent {
            SoundPlayerScreen(
                category = Category.DRACULA,
                viewModel = viewModel,
            )
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.soundButton(Dracula.I_AM.id))
            .performClick()

        verify { soundPlayer.play(Dracula.I_AM) }
    }

    @Test
    fun testButtonClickPlaysSoundForDracula() {
        composeTestRule.setContent {
            SoundPlayerScreen(
                category = Category.DRACULA,
                viewModel = viewModel,
            )
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.soundButton(Dracula.DRACULA.id))
            .performClick()

        verify { soundPlayer.play(Dracula.DRACULA) }
    }
}
