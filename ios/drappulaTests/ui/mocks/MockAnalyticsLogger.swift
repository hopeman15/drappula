import shared
@testable import Drappula

class MockAnalyticsLogger: AnalyticsLogger {
    var logEventCallCount = 0
    var lastTag: String?
    var lastExtras: [String: String]?

    func logEvent(tag: String, extras: [String: String]?) {
        logEventCallCount += 1
        lastTag = tag
        lastExtras = extras
    }
}
