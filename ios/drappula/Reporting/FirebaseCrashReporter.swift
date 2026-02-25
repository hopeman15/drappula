import FirebaseCrashlytics
import shared

class FirebaseCrashReporter: CrashReporter {
    func log(message: String) {
        Crashlytics.crashlytics().log(message)
    }

    func recordException(message: String) {
        let error = NSError(
            domain: "com.hellocuriosity.drappula",
            code: 0,
            userInfo: [NSLocalizedDescriptionKey: message]
        )
        Crashlytics.crashlytics().record(error: error)
    }
}
