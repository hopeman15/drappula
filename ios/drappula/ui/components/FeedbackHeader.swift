import SwiftUI

struct FeedbackHeader: View {
    let onBack: () -> Void

    @Environment(\.drappulaTheme) private var theme

    var body: some View {
        HStack {
            Button {
                onBack()
            } label: {
                Image(systemName: "arrow.left")
                    .foregroundColor(theme.colors.onBackground)
                    .padding(8)
            }

            Text("Feedback")
                .font(theme.typography.headline)
                .fontWeight(.bold)
                .foregroundColor(theme.colors.onBackground)

            Spacer()
        }
        .padding(.vertical, 8)
        .padding(.horizontal, 8)
    }
}
