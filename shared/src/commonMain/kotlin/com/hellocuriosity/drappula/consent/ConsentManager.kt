package com.hellocuriosity.drappula.consent

expect class ConsentManager {
    fun getConsentState(): ConsentState

    fun updateConsent(state: ConsentState)

    fun hasUserResponded(): Boolean
}
