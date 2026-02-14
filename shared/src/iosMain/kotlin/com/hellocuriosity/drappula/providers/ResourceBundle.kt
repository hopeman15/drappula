package com.hellocuriosity.drappula.providers

import platform.Foundation.NSBundle
import platform.Foundation.NSURL

interface ResourceBundle {
    fun urlForSound(
        category: String,
        fileName: String,
    ): NSURL?
}

class NSBundleWrapper : ResourceBundle {
    private val bundle: NSBundle

    constructor() {
        this.bundle = NSBundle.mainBundle
    }

    constructor(bundle: NSBundle) {
        this.bundle = bundle
    }

    override fun urlForSound(
        category: String,
        fileName: String,
    ): NSURL? {
        val name = fileName.substringBeforeLast(".")
        val ext = fileName.substringAfterLast(".", "")
        val url = bundle.URLForResource(name = name, withExtension = ext)
        return url
    }
}
