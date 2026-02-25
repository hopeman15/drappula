package com.hellocuriosity.drappula.reporting

import com.hellocuriosity.drappula.models.Dracula
import kotlin.test.Test
import kotlin.test.assertEquals

class SoundEventTest {
    @Test
    fun testPlayEventTag() {
        val event = SoundEvent.Play(Dracula.I)
        assertEquals(SoundEvent.TAG_PLAY_SOUND, event.tag)
    }

    @Test
    fun testPlayEventExtras() {
        Dracula.entries.forEach { sound ->
            val event = SoundEvent.Play(sound)
            val expected =
                mapOf(
                    SoundEvent.AUDIO to "${sound.displayName} (${sound.category.name})",
                    SoundEvent.CATEGORY to sound.category.name,
                )
            assertEquals(expected, event.extras)
        }
    }
}
