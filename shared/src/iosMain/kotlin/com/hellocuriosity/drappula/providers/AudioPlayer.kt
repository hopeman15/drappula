package com.hellocuriosity.drappula.providers

import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFAudio.AVAudioPlayer
import platform.AVFAudio.AVAudioPlayerDelegateProtocol
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.setActive
import platform.Foundation.NSURL
import platform.darwin.NSObject

interface AudioPlayer {
    fun load(url: NSURL)

    fun prepareToPlay(): Boolean

    fun play(onCompletion: (() -> Unit)? = null): Boolean

    fun stop()

    fun release()
}

@OptIn(ExperimentalForeignApi::class)
private class AudioPlayerDelegate :
    NSObject(),
    AVAudioPlayerDelegateProtocol {
    var completionCallback: (() -> Unit)? = null

    override fun audioPlayerDidFinishPlaying(
        player: AVAudioPlayer,
        successfully: Boolean,
    ) {
        completionCallback?.invoke()
        completionCallback = null
    }
}

@OptIn(ExperimentalForeignApi::class)
class AVAudioPlayerWrapper : AudioPlayer {
    private var player: AVAudioPlayer? = null
    private val delegate = AudioPlayerDelegate()

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

    override fun play(onCompletion: (() -> Unit)?): Boolean {
        delegate.completionCallback = onCompletion
        onCompletion?.let {
            player?.delegate = delegate
        } ?: run { player?.delegate = null }
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
