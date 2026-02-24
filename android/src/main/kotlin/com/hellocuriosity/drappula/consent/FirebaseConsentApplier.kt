package com.hellocuriosity.drappula.consent

import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.perf.FirebasePerformance

object FirebaseConsentApplier {
    fun apply(state: ConsentState) {
        Firebase.analytics.setAnalyticsCollectionEnabled(state.analytics)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(state.crashReporting)
        FirebasePerformance.getInstance().isPerformanceCollectionEnabled = state.analytics

        val analyticsStatus =
            if (state.analytics) {
                FirebaseAnalytics.ConsentStatus.GRANTED
            } else {
                FirebaseAnalytics.ConsentStatus.DENIED
            }

        Firebase.analytics.setConsent(
            mapOf(
                FirebaseAnalytics.ConsentType.ANALYTICS_STORAGE to analyticsStatus,
                FirebaseAnalytics.ConsentType.AD_STORAGE to FirebaseAnalytics.ConsentStatus.DENIED,
                FirebaseAnalytics.ConsentType.AD_USER_DATA to FirebaseAnalytics.ConsentStatus.DENIED,
                FirebaseAnalytics.ConsentType.AD_PERSONALIZATION to FirebaseAnalytics.ConsentStatus.DENIED,
            ),
        )
    }
}
