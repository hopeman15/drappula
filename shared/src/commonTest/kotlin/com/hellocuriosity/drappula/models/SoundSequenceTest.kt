package com.hellocuriosity.drappula.models

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SoundSequenceTest {
    @Test
    fun testSoundSequenceProperties() {
        val sounds = listOf(Dracula.I, Dracula.AM, Dracula.DRACULA)
        val sequence =
            SoundSequence(
                id = 1L,
                name = "I am Dracula",
                sounds = sounds,
            )

        assertEquals(1L, sequence.id)
        assertEquals("I am Dracula", sequence.name)
        assertEquals(3, sequence.sounds.size)
        assertEquals(Dracula.I, sequence.sounds[0])
        assertEquals(Dracula.AM, sequence.sounds[1])
        assertEquals(Dracula.DRACULA, sequence.sounds[2])
    }

    @Test
    fun testSoundSequenceWithEmptySounds() {
        val sequence =
            SoundSequence(
                id = 2L,
                name = "Empty",
                sounds = emptyList(),
            )

        assertTrue(sequence.sounds.isEmpty())
    }

    @Test
    fun testSoundSequenceEquality() {
        val sounds = listOf(Dracula.I, Dracula.AM)
        val sequence1 = SoundSequence(id = 1L, name = "Test", sounds = sounds)
        val sequence2 = SoundSequence(id = 1L, name = "Test", sounds = sounds)

        assertEquals(sequence1, sequence2)
    }
}
