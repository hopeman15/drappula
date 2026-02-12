import SwiftUI
import Testing
@testable import Drappula

@MainActor
struct FeedbackViewTests {
    @Test
    func feedbackViewCanBeInitialized() async {
        let _ = FeedbackView()
    }

    @Test
    func feedbackViewCanBeInitializedWithTheme() async {
        let _ = FeedbackView()
            .drappulaTheme()
    }

    @Test
    func feedbackViewCanBeInitializedInNavigationStack() async {
        let _ = NavigationStack {
            FeedbackView()
        }
        .drappulaTheme()
    }
}
