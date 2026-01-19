package com.hellocuriosity.drappula

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import com.hellocuriosity.drappula.models.Sound

actual class SoundPlayer(
    private val context: Context,
    private val mediaPlayer: MediaPlayer,
) {
    actual fun play(sound: Sound) {
        stop()

        val assetPath = "audio/${sound.category.name.lowercase()}/${sound.fileName}"
        val afd: AssetFileDescriptor = context.assets.openFd(assetPath)
        mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
        afd.close()
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    actual fun stop() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
        mediaPlayer.reset()
    }
}
