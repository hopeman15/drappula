import SwiftUI
import Testing
@testable import Drappula

@MainActor
struct FeedbackTextFieldTests {
    @Test
    func feedbackTextFieldCanBeInitialized() async {
        let _ = FeedbackTextField(
            text: .constant(""),
            placeholder: "Title",
            maxLength: 30
        )
    }

    @Test
    func feedbackTextFieldAcceptsText() async {
        let _ = FeedbackTextField(
            text: .constant("Some text"),
            placeholder: "Title",
            maxLength: 30
        )
    }

    @Test
    func feedbackTextFieldAcceptsCustomMaxLength() async {
        let _ = FeedbackTextField(
            text: .constant(""),
            placeholder: "Name",
            maxLength: 50
        )
    }
}
