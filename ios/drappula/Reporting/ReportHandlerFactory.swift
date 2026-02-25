import shared

enum ReportHandlerFactory {
    static func create() -> ReportHandler {
        return ReportHandler(
            analyticsLogger: FirebaseAnalyticsLogger(),
            crashReporter: FirebaseCrashReporter()
        )
    }
}
