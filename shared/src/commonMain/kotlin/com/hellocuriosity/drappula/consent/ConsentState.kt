package com.hellocuriosity.drappula.consent

data class ConsentState(
    val analytics: Boolean = false,
    val crashReporting: Boolean = false,
)
