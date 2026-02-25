import FirebaseAnalytics
import shared

class FirebaseAnalyticsLogger: AnalyticsLogger {
    func logEvent(tag: String, extras: [String: String]?) {
        Analytics.logEvent(tag, parameters: extras)
    }
}
