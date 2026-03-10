package com.hellocuriosity.drappula.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.SoundPlayer
import com.hellocuriosity.drappula.SoundSequencer
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.data.SoundSequenceRepository
import com.hellocuriosity.drappula.models.Category
import com.hellocuriosity.drappula.models.Dracula
import com.hellocuriosity.drappula.models.SoundSequence
import com.hellocuriosity.drappula.reporting.ReportHandler
import com.hellocuriosity.drappula.ui.soundplayer.DefaultSoundPlayerViewModel
import com.hellocuriosity.drappula.ui.soundplayer.SoundPlayerViewModel
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import org.robolectric.annotation.Config

@Config(application = MockApplication::class)
class SoundPlayerScreenTest : CoroutinesComposeTest() {
    private val soundPlayer: SoundPlayer = mockk(relaxed = true)
    private val soundSequencer: SoundSequencer = mockk(relaxed = true)
    private val soundSequenceRepository: SoundSequenceRepository = mockk()

    private val reportHandler: ReportHandler = mockk(relaxed = true)

    private val viewModel: SoundPlayerViewModel by lazy {
        every { soundSequenceRepository.observeAll() } returns flowOf(emptyList())
        DefaultSoundPlayerViewModel(
            soundPlayer = soundPlayer,
            soundSequencer = soundSequencer,
            soundSequenceRepository = soundSequenceRepository,
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

    @Test
    fun testSequencesSectionHiddenWhenEmpty() {
        composeTestRule.setContent {
            DrappulaTheme {
                SoundPlayerScreen(
                    category = Category.DRACULA,
                    viewModel = viewModel,
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.SEQUENCES_TITLE)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.SEQUENCES_FLOW_ROW)
            .assertDoesNotExist()
    }

    @Test
    fun testSequencesSectionDisplayedWhenSequencesExist() {
        val sequences =
            listOf(
                SoundSequence(
                    id = 1L,
                    name = "I am Dracula",
                    sounds = listOf(Dracula.I, Dracula.AM, Dracula.DRACULA),
                ),
            )
        every { soundSequenceRepository.observeAll() } returns MutableStateFlow(sequences)
        val vm =
            DefaultSoundPlayerViewModel(
                soundPlayer = soundPlayer,
                soundSequencer = soundSequencer,
                soundSequenceRepository = soundSequenceRepository,
                dispatchers = dispatchers,
                reportHandler = reportHandler,
            )

        composeTestRule.setContent {
            DrappulaTheme {
                SoundPlayerScreen(
                    category = Category.DRACULA,
                    viewModel = vm,
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.SEQUENCES_TITLE)
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("My Sequences")

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.SEQUENCES_FLOW_ROW)
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.sequenceButton(1L))
            .assertExists()
            .assertIsDisplayed()
            .assertTextEquals("I am Dracula")
    }
}
