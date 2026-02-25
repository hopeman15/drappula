package com.hellocuriosity.drappula.reporting

actual class ReportHandler(
    private val analyticsLogger: AnalyticsLogger,
    private val crashReporter: CrashReporter,
) {
    actual fun logEvent(event: AnalyticsEvent) {
        analyticsLogger.logEvent(event.tag, event.extras)
    }

    actual fun reportException(
        exception: Throwable,
        report: String,
    ) {
        crashReporter.log("E/$LOG_TAG: $report")
        crashReporter.recordException(report)
    }

    actual fun reportException(exception: Throwable) {
        reportException(exception, exception.message ?: "exception message unavailable")
    }

    companion object {
        private const val LOG_TAG = "DRAPPULA_REPORT"
    }
}
