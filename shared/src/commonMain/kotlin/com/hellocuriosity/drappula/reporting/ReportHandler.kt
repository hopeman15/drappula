package com.hellocuriosity.drappula.reporting

expect class ReportHandler {
    fun logEvent(event: AnalyticsEvent)

    fun reportException(
        exception: Throwable,
        report: String,
    )

    fun reportException(exception: Throwable)
}
