import FirebaseCore
import shared
import SwiftUI

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        FirebaseApp.configure()

        let consentManager = ConsentManager()
        if consentManager.hasUserResponded() {
            FirebaseConsentApplier.apply(consentManager.getConsentState())
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
