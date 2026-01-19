package com.hellocuriosity.drappula.provider

import platform.Foundation.NSURL

class FakeResourceBundle : ResourceBundle {
    var urlForSoundCalled = false
    var lastRequestedCategory: String? = null
    var lastRequestedFileName: String? = null
    var urlToReturn: NSURL? = NSURL(string = "file:///fake/path/sound.mp3")

    override fun urlForSound(
        category: String,
        fileName: String,
    ): NSURL? {
        urlForSoundCalled = true
        lastRequestedCategory = category
        lastRequestedFileName = fileName
        return urlToReturn
    }

    fun reset() {
        urlForSoundCalled = false
        lastRequestedCategory = null
        lastRequestedFileName = null
    }
}
