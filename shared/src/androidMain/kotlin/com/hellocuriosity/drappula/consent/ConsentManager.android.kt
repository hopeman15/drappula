package com.hellocuriosity.drappula.consent

import android.content.Context

actual class ConsentManager(
    private val context: Context,
) {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    actual fun getConsentState(): ConsentState =
        ConsentState(
            analytics = prefs.getBoolean(ANALYTICS, false),
            crashReporting = prefs.getBoolean(CRASH_REPORTING, false),
        )

    actual fun updateConsent(state: ConsentState) {
        prefs
            .edit()
            .putBoolean(ANALYTICS, state.analytics)
            .putBoolean(CRASH_REPORTING, state.crashReporting)
            .putBoolean(HAS_RESPONDED, true)
            .apply()
    }

    actual fun hasUserResponded(): Boolean = prefs.getBoolean(HAS_RESPONDED, false)

    companion object {
        private const val PREFS_NAME = "drappula_consent"
        private const val ANALYTICS = "ANALYTICS"
        private const val CRASH_REPORTING = "CRASH_REPORTING"
        private const val HAS_RESPONDED = "HAS_RESPONDED"
    }
}
