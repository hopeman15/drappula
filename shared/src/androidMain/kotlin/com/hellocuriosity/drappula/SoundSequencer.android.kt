package com.hellocuriosity.drappula

import com.hellocuriosity.drappula.models.Sound

actual class SoundSequencer actual constructor(
    private val soundPlayer: SoundPlayer,
) {
    private var currentIndex = 0
    private var currentSounds: List<Sound> = emptyList()
    private var playing = false

    actual fun play(
        sounds: List<Sound>,
        onComplete: (() -> Unit)?,
    ) {
        stop()
        if (sounds.isEmpty()) {
            onComplete?.invoke()
            return
        }
        currentSounds = sounds
        currentIndex = 0
        playing = true
        playNext(onComplete)
    }

    private fun playNext(onComplete: (() -> Unit)?) {
        if (currentIndex >= currentSounds.size || !playing) {
            playing = false
            onComplete?.invoke()
            return
        }
        val sound = currentSounds[currentIndex]
        currentIndex++
        soundPlayer.play(sound) {
            playNext(onComplete)
        }
    }

    actual fun stop() {
        playing = false
        soundPlayer.stop()
    }

    actual fun isPlaying(): Boolean = playing
}
