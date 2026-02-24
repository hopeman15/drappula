package com.hellocuriosity.drappula.consent

import platform.Foundation.NSUserDefaults

actual class ConsentManager {
    private val defaults = NSUserDefaults.standardUserDefaults

    actual fun getConsentState(): ConsentState =
        ConsentState(
            analytics = defaults.boolForKey(ANALYTICS),
            crashReporting = defaults.boolForKey(CRASH_REPORTING),
        )

    actual fun updateConsent(state: ConsentState) {
        defaults.setBool(state.analytics, forKey = ANALYTICS)
        defaults.setBool(state.crashReporting, forKey = CRASH_REPORTING)
        defaults.setBool(true, forKey = HAS_RESPONDED)
    }

    actual fun hasUserResponded(): Boolean = defaults.boolForKey(HAS_RESPONDED)

    companion object {
        private const val ANALYTICS = "ANALYTICS"
        private const val CRASH_REPORTING = "CRASH_REPORTING"
        private const val HAS_RESPONDED = "HAS_RESPONDED"
    }
}
