package com.hellocuriosity.drappula.provider

import platform.Foundation.NSUserDefaults

actual class PreferenceProvider {
    private val defaults = NSUserDefaults.standardUserDefaults

    actual var isClassicEnabled: Boolean
        get() = defaults.boolForKey(IS_CLASSIC_ENABLED)
        set(value) {
            defaults.setBool(value, forKey = IS_CLASSIC_ENABLED)
        }

    companion object {
        private const val IS_CLASSIC_ENABLED = "isClassicEnabled"
    }
}
