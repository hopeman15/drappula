package com.hellocuriosity.drappula.reporting

interface AnalyticsEvent {
    val tag: String
    val extras: Map<String, String>?
}
