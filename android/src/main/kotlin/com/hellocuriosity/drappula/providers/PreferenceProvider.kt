package com.hellocuriosity.drappula.providers

import android.content.Context
import android.content.SharedPreferences

class PreferenceProvider(
    context: Context,
) {
    companion object {
        private const val DRAPPULA_PREFERENCES = "DRAPPULA_PREFERENCES"
        private const val IS_CLASSIC_ENABLED = "IS_CLASSIC_ENABLED"
    }

    private val prefs = context.getSharedPreferences(DRAPPULA_PREFERENCES, Context.MODE_PRIVATE)

    var isClassicEnabled: Boolean
        get() = prefs.getBoolean(IS_CLASSIC_ENABLED, false)
        set(value) = prefs.putBoolean(IS_CLASSIC_ENABLED, value)
}

private fun SharedPreferences.putBoolean(
    key: String,
    value: Boolean,
) {
    edit().putBoolean(key, value).apply()
}
