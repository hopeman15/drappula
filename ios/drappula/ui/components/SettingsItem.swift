import SwiftUI

struct SettingsItem: View {
    let title: String
    let theme: DrappulaThemeValues

    var body: some View {
        HStack {
            Text(title)
                .font(theme.typography.body)
                .foregroundColor(theme.colors.onBackground)

            Spacer()

            Image(systemName: "chevron.right")
                .foregroundColor(theme.colors.onBackground.opacity(0.5))
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 16)
        .contentShape(Rectangle())
    }
}
