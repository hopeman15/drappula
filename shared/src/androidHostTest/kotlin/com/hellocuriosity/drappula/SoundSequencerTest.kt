package com.hellocuriosity.drappula

import com.hellocuriosity.drappula.models.Dracula
import com.hellocuriosity.drappula.models.Sound
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.mockk.verifyOrder
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SoundSequencerTest {
    private val soundPlayer: SoundPlayer = mockk()
    private val sequencer = SoundSequencer(soundPlayer)

    @AfterTest
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun testPlaySoundsInOrder() {
        val sounds = listOf(Dracula.I, Dracula.AM, Dracula.DRACULA)
        val callbackSlot = slot<() -> Unit>()

        every { soundPlayer.play(any(), capture(callbackSlot)) } just Runs
        every { soundPlayer.stop() } just Runs

        sequencer.play(sounds)

        // First sound is playing
        assertTrue(sequencer.isPlaying())
        verify { soundPlayer.play(Dracula.I, any()) }

        // Simulate first sound completion
        callbackSlot.captured.invoke()
        verify { soundPlayer.play(Dracula.AM, any()) }

        // Simulate second sound completion
        callbackSlot.captured.invoke()
        verify { soundPlayer.play(Dracula.DRACULA, any()) }

        // Simulate third sound completion
        callbackSlot.captured.invoke()
        assertFalse(sequencer.isPlaying())
    }

    @Test
    fun testOnCompleteCalledAfterLastSound() {
        val sounds = listOf(Dracula.I)
        val callbackSlot = slot<() -> Unit>()
        var completed = false

        every { soundPlayer.play(any(), capture(callbackSlot)) } just Runs
        every { soundPlayer.stop() } just Runs

        sequencer.play(sounds) { completed = true }

        // Simulate sound completion
        callbackSlot.captured.invoke()
        assertTrue(completed)
        assertFalse(sequencer.isPlaying())
    }

    @Test
    fun testEmptyListCallsOnCompleteImmediately() {
        var completed = false

        every { soundPlayer.stop() } just Runs

        sequencer.play(emptyList()) { completed = true }

        assertTrue(completed)
        assertFalse(sequencer.isPlaying())
    }

    @Test
    fun testStopHaltsSequenceMidPlay() {
        val sounds = listOf(Dracula.I, Dracula.AM, Dracula.DRACULA)
        val callbackSlot = slot<() -> Unit>()

        every { soundPlayer.play(any(), capture(callbackSlot)) } just Runs
        every { soundPlayer.stop() } just Runs

        sequencer.play(sounds)
        assertTrue(sequencer.isPlaying())

        // Stop mid-sequence
        sequencer.stop()
        assertFalse(sequencer.isPlaying())

        // Simulate callback from stopped sound - should not continue
        callbackSlot.captured.invoke()
        // Only I should have been played, not AM
        verify(exactly = 1) { soundPlayer.play(Dracula.I, any()) }
        verify(exactly = 0) { soundPlayer.play(Dracula.AM, any()) }
    }

    @Test
    fun testPlayOrderIsCorrect() {
        val sounds = listOf(Dracula.LISTEN, Dracula.TO, Dracula.THEM)
        val callbackSlot = slot<() -> Unit>()

        every { soundPlayer.play(any(), capture(callbackSlot)) } just Runs
        every { soundPlayer.stop() } just Runs

        sequencer.play(sounds)

        verifyOrder {
            soundPlayer.play(Dracula.LISTEN, any())
        }

        callbackSlot.captured.invoke()
        verifyOrder {
            soundPlayer.play(Dracula.LISTEN, any())
            soundPlayer.play(Dracula.TO, any())
        }

        callbackSlot.captured.invoke()
        verifyOrder {
            soundPlayer.play(Dracula.LISTEN, any())
            soundPlayer.play(Dracula.TO, any())
            soundPlayer.play(Dracula.THEM, any())
        }
    }
}
