package com.hellocuriosity.drappula.reporting

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics

actual class ReportHandler(
    private val analytics: FirebaseAnalytics,
    private val crashlytics: FirebaseCrashlytics,
    private val bundle: Bundle = Bundle(),
) {
    actual fun logEvent(event: AnalyticsEvent) {
        event.extras?.forEach {
            bundle.putString(it.key, it.value)
        }
        analytics.logEvent(event.tag, bundle)
    }

    actual fun reportException(
        exception: Throwable,
        report: String,
    ) {
        crashlytics.log("E/$LOG_TAG: $report")
        crashlytics.recordException(exception)
    }

    actual fun reportException(exception: Throwable) {
        reportException(exception, exception.message ?: "exception message unavailable")
    }

    companion object {
        private const val LOG_TAG = "DRAPPULA_REPORT"
    }
}
