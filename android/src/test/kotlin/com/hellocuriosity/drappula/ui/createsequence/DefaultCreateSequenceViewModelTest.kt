package com.hellocuriosity.drappula.ui.createsequence

import app.cash.turbine.test
import com.hellocuriosity.drappula.coroutines.CoroutinesTestCase
import com.hellocuriosity.drappula.data.SoundSequenceRepository
import com.hellocuriosity.drappula.models.Dracula
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
class DefaultCreateSequenceViewModelTest : CoroutinesTestCase(StandardTestDispatcher()) {
    private val soundSequenceRepository: SoundSequenceRepository = mockk()

    private val viewModel: CreateSequenceViewModel by lazy {
        DefaultCreateSequenceViewModel(
            soundSequenceRepository = soundSequenceRepository,
            dispatchers = dispatchers,
        )
    }

    @After
    fun teardown() {
        confirmVerified(soundSequenceRepository)
    }

    @Test
    fun testInitialState() =
        runBlockingTest {
            viewModel.state.test {
                val state = awaitItem()
                assertTrue(state.selectedSounds.isEmpty())
                assertEquals("", state.sequenceName)
                assertFalse(state.isSaving)
                assertFalse(state.savedSuccessfully)
                assertNull(state.error)
            }
        }

    @Test
    fun testAddSound() =
        runBlockingTest {
            viewModel.state.test {
                awaitItem()

                viewModel.addSound(Dracula.I)
                assertEquals(listOf(Dracula.I), awaitItem().selectedSounds)

                viewModel.addSound(Dracula.AM)
                assertEquals(listOf(Dracula.I, Dracula.AM), awaitItem().selectedSounds)

                viewModel.addSound(Dracula.DRACULA)
                val state = awaitItem()
                assertEquals(listOf(Dracula.I, Dracula.AM, Dracula.DRACULA), state.selectedSounds)
            }
        }

    @Test
    fun testSequenceNameDerivedFromSelectedSounds() =
        runBlockingTest {
            viewModel.state.test {
                assertEquals("", awaitItem().sequenceName)

                viewModel.addSound(Dracula.I)
                assertEquals("i", awaitItem().sequenceName)

                viewModel.addSound(Dracula.AM)
                assertEquals("i am", awaitItem().sequenceName)

                viewModel.addSound(Dracula.DRACULA)
                assertEquals("i am dracula", awaitItem().sequenceName)
            }
        }

    @Test
    fun testRemoveSound() =
        runBlockingTest {
            viewModel.state.test {
                awaitItem()

                viewModel.addSound(Dracula.I)
                awaitItem()
                viewModel.addSound(Dracula.AM)
                awaitItem()
                viewModel.addSound(Dracula.DRACULA)
                awaitItem()

                viewModel.removeSound(1)
                val state = awaitItem()
                assertEquals(listOf(Dracula.I, Dracula.DRACULA), state.selectedSounds)
                assertEquals("i dracula", state.sequenceName)
            }
        }

    @Test
    fun testRemoveSoundOutOfBoundsDoesNothing() =
        runBlockingTest {
            viewModel.state.test {
                awaitItem()

                viewModel.addSound(Dracula.I)
                val stateAfterAdd = awaitItem()
                assertEquals(listOf(Dracula.I), stateAfterAdd.selectedSounds)

                viewModel.removeSound(5)
                // StateFlow won't emit since the list is unchanged (structural equality)
                expectNoEvents()
            }
        }

    @Test
    fun testSaveSuccess() =
        runBlockingTest {
            every { soundSequenceRepository.create(any(), any()) } just runs

            viewModel.state.test {
                awaitItem()

                viewModel.addSound(Dracula.I)
                awaitItem()
                viewModel.addSound(Dracula.AM)
                awaitItem()
                viewModel.addSound(Dracula.DRACULA)
                awaitItem()

                viewModel.save()

                val savingState = awaitItem()
                assertTrue(savingState.isSaving)
                assertNull(savingState.error)

                val savedState = awaitItem()
                assertFalse(savedState.isSaving)
                assertTrue(savedState.savedSuccessfully)
            }

            verify {
                soundSequenceRepository.create(
                    "i am dracula",
                    listOf(Dracula.I, Dracula.AM, Dracula.DRACULA),
                )
            }
        }

    @Test
    fun testSaveWithNoSoundsDoesNothing() =
        runBlockingTest {
            viewModel.state.test {
                awaitItem()

                viewModel.save()
                expectNoEvents()
            }
        }

    @Test
    fun testSaveHandlesException() =
        runBlockingTest {
            val exception = RuntimeException("Database error")
            every { soundSequenceRepository.create(any(), any()) } throws exception

            viewModel.state.test {
                awaitItem()

                viewModel.addSound(Dracula.I)
                awaitItem()

                viewModel.save()

                val savingState = awaitItem()
                assertTrue(savingState.isSaving)

                val errorState = awaitItem()
                assertFalse(errorState.isSaving)
                assertEquals(exception, errorState.error)
            }

            verify { soundSequenceRepository.create("i", listOf(Dracula.I)) }
        }

    @Test
    fun testReset() =
        runBlockingTest {
            viewModel.state.test {
                awaitItem()

                viewModel.addSound(Dracula.I)
                awaitItem()

                viewModel.reset()

                val resetState = awaitItem()
                assertTrue(resetState.selectedSounds.isEmpty())
                assertEquals("", resetState.sequenceName)
                assertFalse(resetState.isSaving)
                assertFalse(resetState.savedSuccessfully)
                assertNull(resetState.error)
            }
        }
}
