import SwiftUI

struct FeedbackTextField: View {
    @Binding var text: String
    let placeholder: String
    let maxLength: Int

    @Environment(\.drappulaTheme) private var theme

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            TextField(
                placeholder,
                text: $text,
                prompt: Text(placeholder)
                    .foregroundColor(theme.colors.onBackground.opacity(0.5))
            )
                .font(theme.typography.body)
                .foregroundColor(theme.colors.onBackground)
                .padding(10)
                .background(
                    RoundedRectangle(cornerRadius: 6)
                        .stroke(
                            theme.colors.onBackground.opacity(0.5),
                            lineWidth: 1
                        )
                )
                .onChange(of: text) { _, newValue in
                    if newValue.count > maxLength {
                        text = String(newValue.prefix(maxLength))
                    }
                }

            Text("\(text.count)/\(maxLength)")
                .font(theme.typography.caption)
                .foregroundColor(theme.colors.onBackground)
        }
        .padding(.horizontal, 16)
    }
}
