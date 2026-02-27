package com.hellocuriosity.drappula

import com.hellocuriosity.drappula.models.Sound

expect class SoundSequencer(
    soundPlayer: SoundPlayer,
) {
    fun play(
        sounds: List<Sound>,
        onComplete: (() -> Unit)? = null,
    )

    fun stop()

    fun isPlaying(): Boolean
}
