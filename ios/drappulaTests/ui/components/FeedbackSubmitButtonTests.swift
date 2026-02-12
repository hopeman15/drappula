import Testing
@testable import Drappula

@MainActor
struct FeedbackSubmitButtonTests {
    @Test
    func feedbackSubmitButtonCanBeInitialized() async {
        var submitCount = 0
        let _ = FeedbackSubmitButton(
            isEnabled: true,
            onSubmit: { submitCount += 1 }
        )

        #expect(submitCount == 0)
    }

    @Test
    func feedbackSubmitButtonAcceptsDisabledState() async {
        let _ = FeedbackSubmitButton(
            isEnabled: false,
            onSubmit: {}
        )
    }
}
