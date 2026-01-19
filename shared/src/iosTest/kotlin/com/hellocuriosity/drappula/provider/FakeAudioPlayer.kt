package com.hellocuriosity.drappula.provider

import platform.Foundation.NSURL

class FakeAudioPlayer : AudioPlayer {
    var loadCalled = false
    var lastLoadedUrl: NSURL? = null
    var prepareToPlayCalled = false
    var playCalled = false
    var stopCalled = false

    override fun load(url: NSURL) {
        loadCalled = true
        lastLoadedUrl = url
    }

    override fun prepareToPlay(): Boolean {
        prepareToPlayCalled = true
        return true
    }

    override fun play(): Boolean {
        playCalled = true
        return true
    }

    override fun stop() {
        stopCalled = true
    }

    fun reset() {
        loadCalled = false
        lastLoadedUrl = null
        prepareToPlayCalled = false
        playCalled = false
        stopCalled = false
    }
}
