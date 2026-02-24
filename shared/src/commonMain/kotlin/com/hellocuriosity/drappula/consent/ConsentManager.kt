package com.hellocuriosity.drappula.consent

const val PRIVACY_POLICY_URL = "https://sites.google.com/view/drappula/home"

expect class ConsentManager {
    fun getConsentState(): ConsentState

    fun updateConsent(state: ConsentState)

    fun hasUserResponded(): Boolean
}
