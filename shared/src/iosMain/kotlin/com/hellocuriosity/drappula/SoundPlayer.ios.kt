package com.hellocuriosity.drappula

import com.hellocuriosity.drappula.models.Sound
import com.hellocuriosity.drappula.providers.AudioPlayer
import com.hellocuriosity.drappula.providers.ResourceBundle

actual class SoundPlayer(
    private val bundle: ResourceBundle,
    private val audioPlayer: AudioPlayer,
) {
    actual fun play(sound: Sound) {
        stop()

        val category = sound.category.name.lowercase()
        val url = bundle.urlForSound(category, sound.fileName) ?: return

        audioPlayer.load(url)
        audioPlayer.prepareToPlay()
        audioPlayer.play()
    }

    actual fun stop() {
        audioPlayer.stop()
    }

    actual fun release() {
        audioPlayer.release()
    }
}
