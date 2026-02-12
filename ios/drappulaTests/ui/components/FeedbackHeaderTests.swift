import Testing
@testable import Drappula

@MainActor
struct FeedbackHeaderTests {
    @Test
    func feedbackHeaderCanBeInitialized() async {
        var backCount = 0
        let _ = FeedbackHeader(onBack: { backCount += 1 })

        #expect(backCount == 0)
    }
}
