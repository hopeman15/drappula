import shared
import SwiftUI

struct FeedbackTypePicker: View {
    @Binding var selectedType: Feedback.Type_
    let feedbackTypes: [(String, Feedback.Type_)]

    @Environment(\.drappulaTheme) private var theme

    var body: some View {
        HStack {
            Text("Type")
                .font(theme.typography.body)
                .foregroundColor(theme.colors.onBackground)

            Spacer()

            Picker("Type", selection: $selectedType) {
                ForEach(feedbackTypes, id: \.0) { label, type in
                    Text(label).tag(type)
                }
            }
            .pickerStyle(.menu)
            .tint(theme.colors.onBackground)
        }
        .padding(.horizontal, 16)
    }
}
