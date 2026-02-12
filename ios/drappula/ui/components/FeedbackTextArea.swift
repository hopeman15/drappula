import SwiftUI

struct FeedbackTextArea: View {
    @Binding var text: String
    let placeholder: String
    let maxLength: Int

    @Environment(\.drappulaTheme) private var theme

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            ZStack(alignment: .topLeading) {
                if text.isEmpty {
                    Text(placeholder)
                        .font(theme.typography.body)
                        .foregroundColor(theme.colors.onBackground.opacity(0.5))
                        .padding(.horizontal, 11)
                        .padding(.vertical, 14)
                }
                TextEditor(text: $text)
                    .font(theme.typography.body)
                    .frame(minHeight: 150)
                    .scrollContentBackground(.hidden)
                    .padding(6)
                    .foregroundColor(theme.colors.onBackground)
                    .onChange(of: text) { _, newValue in
                        if newValue.count > maxLength {
                            text = String(newValue.prefix(maxLength))
                        }
                    }
            }
            .background(
                RoundedRectangle(cornerRadius: 6)
                    .stroke(
                        theme.colors.onBackground.opacity(0.5),
                        lineWidth: 1
                    )
            )

            Text("\(text.count)/\(maxLength)")
                .font(theme.typography.caption)
                .foregroundColor(theme.colors.onBackground)
        }
        .padding(.horizontal, 16)
    }
}
