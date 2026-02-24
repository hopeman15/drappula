import FirebaseAnalytics
import FirebaseCrashlytics
import FirebasePerformance
import Foundation

struct ConsentStateIOS {
    let analytics: Bool
    let crashReporting: Bool
}

class ConsentManagerIOS {
    static let shared = ConsentManagerIOS()

    private let defaults = UserDefaults.standard

    private enum Keys {
        static let analytics = "consent_analytics"
        static let crashReporting = "consent_crash_reporting"
        static let hasResponded = "consent_has_responded"
    }

    var consentState: ConsentStateIOS {
        ConsentStateIOS(
            analytics: defaults.bool(forKey: Keys.analytics),
            crashReporting: defaults.bool(forKey: Keys.crashReporting)
        )
    }

    func updateConsent(_ state: ConsentStateIOS) {
        defaults.set(state.analytics, forKey: Keys.analytics)
        defaults.set(state.crashReporting, forKey: Keys.crashReporting)
        defaults.set(true, forKey: Keys.hasResponded)
        applyToFirebase(state)
    }

    func hasUserResponded() -> Bool {
        defaults.bool(forKey: Keys.hasResponded)
    }

    func applyToFirebase(_ state: ConsentStateIOS) {
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
