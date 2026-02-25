package com.hellocuriosity.drappula.reporting

interface AnalyticsLogger {
    fun logEvent(
        tag: String,
        extras: Map<String, String>?,
    )
}
