package com.hellocuriosity.drappula.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.hellocuriosity.drappula.MockApplication
import com.hellocuriosity.drappula.SoundPlayer
import com.hellocuriosity.drappula.coroutines.CoroutinesComposeTest
import com.hellocuriosity.drappula.navigation.Tab
import com.hellocuriosity.drappula.reporting.ReportHandler
import com.hellocuriosity.drappula.ui.feedback.FeedbackViewModel
import com.hellocuriosity.drappula.ui.soundplayer.DefaultSoundPlayerViewModel
import com.hellocuriosity.drappula.ui.soundplayer.SoundPlayerViewModel
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import io.mockk.mockk
import org.junit.Test
import org.robolectric.annotation.Config

@Config(application = MockApplication::class)
class DashboardScreenTest : CoroutinesComposeTest() {
    private val soundPlayer: SoundPlayer = mockk(relaxed = true)
    private val feedbackViewModel: FeedbackViewModel = mockk(relaxed = true)

    private val reportHandler: ReportHandler = mockk(relaxed = true)

    private val viewModel: SoundPlayerViewModel by lazy {
        DefaultSoundPlayerViewModel(
            soundPlayer = soundPlayer,
            dispatchers = dispatchers,
            reportHandler = reportHandler,
        )
    }

    @Test
    fun testDashboardScreenDisplaysNavigationBar() {
        composeTestRule.setContent {
            DrappulaTheme {
                DashboardScreen(
                    soundPlayerViewModel = viewModel,
                    feedbackViewModel = feedbackViewModel,
                )
            }
        }

        composeTestRule
            .onNodeWithTag(DashboardScreenTestTags.NAVIGATION_BAR)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testAllTabsAreDisplayed() {
        composeTestRule.setContent {
            DrappulaTheme {
                DashboardScreen(
                    soundPlayerViewModel = viewModel,
                    feedbackViewModel = feedbackViewModel,
                )
            }
        }

        Tab.entries.forEach { tab ->
            composeTestRule
                .onNodeWithTag(DashboardScreenTestTags.tabItem(tab))
                .assertExists()
                .assertIsDisplayed()
        }
    }

    @Test
    fun testAudioTabIsSelectedByDefault() {
        composeTestRule.setContent {
            DrappulaTheme {
                DashboardScreen(
                    soundPlayerViewModel = viewModel,
                    feedbackViewModel = feedbackViewModel,
                )
            }
        }

        composeTestRule
            .onNodeWithTag(DashboardScreenTestTags.tabItem(Tab.AUDIO))
            .assertIsSelected()

        composeTestRule
            .onNodeWithTag(DashboardScreenTestTags.tabItem(Tab.SETTINGS))
            .assertIsNotSelected()
    }

    @Test
    fun testSoundPlayerScreenDisplayedByDefault() {
        composeTestRule.setContent {
            DrappulaTheme {
                DashboardScreen(
                    soundPlayerViewModel = viewModel,
                    feedbackViewModel = feedbackViewModel,
                )
            }
        }

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.SCREEN)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testClickingSettingsTabShowsSettingsScreen() {
        composeTestRule.setContent {
            DrappulaTheme {
                DashboardScreen(
                    soundPlayerViewModel = viewModel,
                    feedbackViewModel = feedbackViewModel,
                )
            }
        }

        composeTestRule
            .onNodeWithTag(DashboardScreenTestTags.tabItem(Tab.SETTINGS))
            .performClick()

        composeTestRule
            .onNodeWithTag(DashboardScreenTestTags.tabItem(Tab.SETTINGS))
            .assertIsSelected()

        composeTestRule
            .onNodeWithTag(SettingsTestTags.SCREEN)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun testClickingAudioTabShowsSoundPlayerScreen() {
        composeTestRule.setContent {
            DrappulaTheme {
                DashboardScreen(
                    soundPlayerViewModel = viewModel,
                    feedbackViewModel = feedbackViewModel,
                )
            }
        }

        // First navigate to Settings
        composeTestRule
            .onNodeWithTag(DashboardScreenTestTags.tabItem(Tab.SETTINGS))
            .performClick()

        // Then navigate back to Audio
        composeTestRule
            .onNodeWithTag(DashboardScreenTestTags.tabItem(Tab.AUDIO))
            .performClick()

        composeTestRule
            .onNodeWithTag(DashboardScreenTestTags.tabItem(Tab.AUDIO))
            .assertIsSelected()

        composeTestRule
            .onNodeWithTag(SoundPlayerTestTags.SCREEN)
            .assertExists()
            .assertIsDisplayed()
    }
}
