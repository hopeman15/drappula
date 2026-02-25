package com.hellocuriosity.drappula.reporting

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Test

class ReportHandlerTest {
    private val analytics: FirebaseAnalytics = mockk()
    private val crashlytics: FirebaseCrashlytics = mockk()
    private val bundle: Bundle = mockk()

    private val reportHandler =
        ReportHandler(
            analytics = analytics,
            crashlytics = crashlytics,
            bundle = bundle,
        )

    @After
    fun teardown() {
        confirmVerified(analytics, crashlytics, bundle)
    }

    @Test
    fun testLogEventWithExtras() {
        every { bundle.putString(any(), any()) } returns Unit
        every { analytics.logEvent(any(), any()) } returns Unit

        val event =
            object : AnalyticsEvent {
                override val tag: String = "TEST_TAG"
                override val extras: Map<String, String> = mapOf("KEY" to "VALUE")
            }
        reportHandler.logEvent(event)

        verify { bundle.putString("KEY", "VALUE") }
        verify { analytics.logEvent("TEST_TAG", bundle) }
    }

    @Test
    fun testLogEventNoExtras() {
        every { analytics.logEvent(any(), any()) } returns Unit

        val event =
            object : AnalyticsEvent {
                override val tag: String = "TEST_TAG"
                override val extras: Map<String, String>? = null
            }
        reportHandler.logEvent(event)

        verify { analytics.logEvent("TEST_TAG", bundle) }
    }

    @Test
    fun testReportException() {
        val message = "Test error"
        val exception = Exception(message)

        every { crashlytics.log(any()) } returns Unit
        every { crashlytics.recordException(any()) } returns Unit

        reportHandler.reportException(exception)

        verify { crashlytics.log("E/DRAPPULA_REPORT: $message") }
        verify { crashlytics.recordException(exception) }
    }

    @Test
    fun testReportExceptionWithCustomReport() {
        val exception = Exception("original")
        val report = "Custom report message"

        every { crashlytics.log(any()) } returns Unit
        every { crashlytics.recordException(any()) } returns Unit

        reportHandler.reportException(exception, report)

        verify { crashlytics.log("E/DRAPPULA_REPORT: $report") }
        verify { crashlytics.recordException(exception) }
    }
}
