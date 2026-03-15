import FirebaseAnalytics
import FirebaseCrashlytics
import FirebasePerformance
import shared

enum FirebaseConsentApplier {
    static func apply(_ state: ConsentState) {
        Analytics.setAnalyticsCollectionEnabled(state.analytics)
        Crashlytics.crashlytics().setCrashlyticsCollectionEnabled(state.crashReporting)
        Performance.sharedInstance().isDataCollectionEnabled = state.analytics
        Performance.sharedInstance().isInstrumentationEnabled = state.analytics

        Analytics.setConsent([
            .analyticsStorage: state.analytics ? .granted : .denied,
            .adStorage: .denied,
            .adUserData: .denied,
            .adPersonalization: .denied
        ])
    }
}
