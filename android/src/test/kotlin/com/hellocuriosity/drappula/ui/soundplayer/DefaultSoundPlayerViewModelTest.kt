package com.hellocuriosity.drappula.ui.soundplayer

import app.cash.turbine.test
import com.hellocuriosity.drappula.SoundPlayer
import com.hellocuriosity.drappula.coroutines.CoroutinesTestCase
import com.hellocuriosity.drappula.models.Dracula
import com.hellocuriosity.drappula.ui.simulateCleared
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.After
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultSoundPlayerViewModelTest : CoroutinesTestCase(StandardTestDispatcher()) {
    private val soundPlayer: SoundPlayer = mockk()

    private val viewModel: SoundPlayerViewModel by lazy {
        DefaultSoundPlayerViewModel(
            soundPlayer = soundPlayer,
            dispatchers = dispatchers,
        )
    }

    @After
    fun teardown() {
        confirmVerified(soundPlayer)
    }

    @Test
    fun testInitialStateIsNotPlayingWithNoError() =
        runBlockingTest {
            viewModel.state.test {
                val initialState = awaitItem()
                assertFalse(initialState.isPlaying)
                assertNull(initialState.error)
            }
        }

    @Test
    fun testPlaySoundUpdatesStateToPlayingThenNotPlaying() =
        runBlockingTest {
            val sound = Dracula.I
            every { soundPlayer.play(sound) } just runs

            viewModel.state.test {
                // Initial state
                val initialState = awaitItem()
                assertFalse(initialState.isPlaying)

                // Trigger playSound
                viewModel.playSound(sound)

                // State transitions to playing
                val playingState = awaitItem()
                assertTrue(playingState.isPlaying)
                assertNull(playingState.error)

                // State transitions back to not playing
                val finishedState = awaitItem()
                assertFalse(finishedState.isPlaying)
                assertNull(finishedState.error)
            }

            verify { soundPlayer.play(sound) }
        }

    @Test
    fun testPlaySoundWithDraculaSoundPlaysCorrectly() =
        runBlockingTest {
            val sound = Dracula.DRACULA
            every { soundPlayer.play(sound) } just runs

            viewModel.state.test {
                assertFalse(awaitItem().isPlaying)

                viewModel.playSound(sound)

                assertTrue(awaitItem().isPlaying)
                assertFalse(awaitItem().isPlaying)
            }

            verify { soundPlayer.play(sound) }
        }

    @Test
    fun testPlaySoundHandlesExceptionAndSetsErrorState() =
        runBlockingTest {
            val sound = Dracula.I
            val exception = RuntimeException("Audio playback failed")
            every { soundPlayer.play(sound) } throws exception

            viewModel.state.test {
                assertFalse(awaitItem().isPlaying)

                viewModel.playSound(sound)

                // State transitions to playing
                assertTrue(awaitItem().isPlaying)

                // State transitions to error
                val errorState = awaitItem()
                assertFalse(errorState.isPlaying)
                assertEquals(exception, errorState.error)
            }

            verify { soundPlayer.play(sound) }
        }

    @Test
    fun testPlayingSoundClearsPreviousError() =
        runBlockingTest {
            val sound = Dracula.I
            val exception = RuntimeException("First failure")

            // First call throws, second succeeds
            every { soundPlayer.play(sound) } throws exception andThen Unit

            viewModel.state.test {
                assertFalse(awaitItem().isPlaying)

                // First play - fails
                viewModel.playSound(sound)
                assertTrue(awaitItem().isPlaying)
                val errorState = awaitItem()
                assertFalse(errorState.isPlaying)
                assertEquals(exception, errorState.error)

                // Second play - succeeds and clears error
                viewModel.playSound(sound)
                val playingAgainState = awaitItem()
                assertTrue(playingAgainState.isPlaying)
                assertNull(playingAgainState.error)

                val finishedState = awaitItem()
                assertFalse(finishedState.isPlaying)
                assertNull(finishedState.error)
            }

            verify(exactly = 2) { soundPlayer.play(sound) }
        }

    @Test
    fun testOnClearedReleasesSoundPlayer() =
        runBlockingTest {
            every { soundPlayer.release() } just runs

            viewModel.state.test {
                awaitItem()
            }

            viewModel.simulateCleared()
            verify { soundPlayer.release() }
        }
}
