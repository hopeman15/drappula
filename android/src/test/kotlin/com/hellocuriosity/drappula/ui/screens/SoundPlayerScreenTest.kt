package com.hellocuriosity.drappula.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.SoundPlayer
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.models.Category
import com.hellocuriosity.drappula.models.Dracula
import com.hellocuriosity.drappula.reporting.ReportHandler
import com.hellocuriosity.drappula.ui.soundplayer.DefaultSoundPlayerViewModel
import com.hellocuriosity.drappula.ui.soundplayer.SoundPlayerViewModel
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.robolectric.annotation.Config

@Config(application = MockApplication::class)
class SoundPlayerScreenTest : CoroutinesComposeTest() {
    private val soundPlayer: SoundPlayer = mockk(relaxed = true)

    private val reportHandler: ReportHandler = mockk(relaxed = true)

    private val viewModel: SoundPlayerViewModel by lazy {
        DefaultSoundPlayerViewModel(
            soundPlayer = soundPlayer,
            dispatchers = dispatchers,
            reportHandler = reportHandler,
        )
    }

    @Test
    fun testSoundPlayerScreenDisplaysCorrectly() {
        composeTestRule.setContent {
            DrappulaTheme {
                SoundPlayerScreen(
                    category = Category.DRACULA,
                    viewModel = viewModel,
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.SCREEN)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.TITLE)
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals(Category.DRACULA.title)

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.FLOW_ROW)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testAllSoundButtonsAreDisplayed() {
        composeTestRule.setContent {
            DrappulaTheme {
                SoundPlayerScreen(
                    category = Category.DRACULA,
                    viewModel = viewModel,
                )
            }
        }

        Dracula.entries.forEach { sound ->
            composeTestRule
                .onNodeWithTag(SoundPlayerTestTags.soundButton(sound.id))
                .assertExists()
                .assertIsDisplayed()
                .assertTextEquals(sound.displayName)
        }
    }

    @Test
    fun testButtonsAreEnabledInitially() {
        composeTestRule.setContent {
            DrappulaTheme {
                SoundPlayerScreen(
                    category = Category.DRACULA,
                    viewModel = viewModel,
                )
            }
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
            DrappulaTheme {
                SoundPlayerScreen(
                    category = Category.DRACULA,
                    viewModel = viewModel,
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.soundButton(Dracula.DRACULA.id))
            .performClick()

        verify { soundPlayer.play(Dracula.DRACULA) }
    }

    @Test
    fun testButtonClickPlaysSoundForDracula() {
        composeTestRule.setContent {
            DrappulaTheme {
                SoundPlayerScreen(
                    category = Category.DRACULA,
                    viewModel = viewModel,
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.soundButton(Dracula.DRACULA.id))
            .performClick()

        verify { soundPlayer.play(Dracula.DRACULA) }
    }
}
