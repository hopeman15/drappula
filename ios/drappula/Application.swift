import FirebaseCore
import SwiftUI

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        FirebaseApp.configure()

        let consentManager = ConsentManagerIOS.shared
        if consentManager.hasUserResponded() {
            consentManager.applyToFirebase(consentManager.consentState)
        }
        return true
    }
}

@main
struct Application: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
