import SwiftUI
import Testing
@testable import Drappula
import shared

@MainActor
struct FeedbackTypePickerTests {
    @Test
    func feedbackTypePickerCanBeInitialized() async {
        let _ = FeedbackTypePicker(
            selectedType: .constant(.enhancement),
            feedbackTypes: [("Enhancement", .enhancement)]
        )
    }

    @Test
    func feedbackTypePickerAcceptsMultipleTypes() async {
        let types: [(String, Feedback.Type_)] = [
            ("Enhancement", .enhancement),
            ("Feature Request", .feature),
            ("Fix", .fix),
            ("Other", .other)
        ]
        let _ = FeedbackTypePicker(
            selectedType: .constant(.fix),
            feedbackTypes: types
        )
    }
}
