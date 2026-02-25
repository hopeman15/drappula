import shared
@testable import Drappula

class MockCrashReporter: CrashReporter {
    var logCallCount = 0
    var recordExceptionCallCount = 0
    var lastLogMessage: String?
    var lastExceptionMessage: String?

    func log(message: String) {
        logCallCount += 1
        lastLogMessage = message
    }

    func recordException(message: String) {
        recordExceptionCallCount += 1
        lastExceptionMessage = message
    }
}
