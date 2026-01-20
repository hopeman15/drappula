package com.hellocuriosity.drappula.provider

import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFAudio.AVAudioPlayer
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.setActive
import platform.Foundation.NSURL

interface AudioPlayer {
    fun load(url: NSURL)

    fun prepareToPlay(): Boolean

    fun play(): Boolean

    fun stop()

    fun release()
}

@OptIn(ExperimentalForeignApi::class)
class AVAudioPlayerWrapper : AudioPlayer {
    private var player: AVAudioPlayer? = null

    init {
        configureAudioSession()
    }

    private fun configureAudioSession() {
        val session = AVAudioSession.sharedInstance()
        session.setCategory(AVAudioSessionCategoryPlayback, error = null)
        session.setActive(true, error = null)
    }

    override fun load(url: NSURL) {
        player = AVAudioPlayer(contentsOfURL = url, error = null)
    }

    override fun prepareToPlay(): Boolean {
        val result = player?.prepareToPlay() ?: false
        return result
    }

    override fun play(): Boolean {
        val result = player?.play() ?: false
        return result
    }

    override fun stop() {
        player?.stop()
    }

    override fun release() {
        stop()
        player = null
    }
}
