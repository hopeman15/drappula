import shared

enum MockReportHandlerFactory {
    static func create() -> ReportHandler {
        return ReportHandler(
            analyticsLogger: MockAnalyticsLogger(),
            crashReporter: MockCrashReporter()
        )
    }
}
