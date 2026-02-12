import SwiftUI
import Testing
@testable import Drappula

@MainActor
struct FeedbackTextAreaTests {
    @Test
    func feedbackTextAreaCanBeInitialized() async {
        let _ = FeedbackTextArea(
            text: .constant(""),
            placeholder: "Message",
            maxLength: 300
        )
    }

    @Test
    func feedbackTextAreaAcceptsText() async {
        let _ = FeedbackTextArea(
            text: .constant("Some message"),
            placeholder: "Message",
            maxLength: 300
        )
    }

    @Test
    func feedbackTextAreaAcceptsCustomMaxLength() async {
        let _ = FeedbackTextArea(
            text: .constant(""),
            placeholder: "Description",
            maxLength: 500
        )
    }
}
