package com.hellocuriosity.drappula

import com.hellocuriosity.drappula.models.Sound

expect class SoundPlayer {
    fun play(
        sound: Sound,
        onCompletion: (() -> Unit)? = null,
    )

    fun stop()

    fun release()
}
