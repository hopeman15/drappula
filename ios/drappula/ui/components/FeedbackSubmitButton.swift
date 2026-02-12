import SwiftUI

struct FeedbackSubmitButton: View {
    let isEnabled: Bool
    let onSubmit: () -> Void

    @Environment(\.drappulaTheme) private var theme

    var body: some View {
        Button {
            onSubmit()
        } label: {
            Text("Submit")
                .font(theme.typography.button)
                .foregroundColor(
                    isEnabled
                        ? theme.colors.background
                        : theme.colors.background.opacity(0.3)
                )
                .padding(.horizontal, 24)
                .padding(.vertical, 12)
                .background(
                    isEnabled
                        ? theme.colors.onBackground
                        : theme.colors.onBackground.opacity(0.3)
                )
                .clipShape(RoundedRectangle(cornerRadius: 8))
        }
        .disabled(!isEnabled)
        .padding(.top, 24)
    }
}
