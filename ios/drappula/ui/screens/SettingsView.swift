import SwiftUI

struct SettingsView: View {
    @Environment(\.drappulaTheme) private var theme

    var body: some View {
        ZStack {
            theme.gradients.background
                .ignoresSafeArea()

            Text("Settings")
                .font(theme.typography.display)
                .fontWeight(.bold)
                .foregroundColor(theme.colors.onBackground)
        }
    }
}

struct SettingsView_Previews: PreviewProvider {
    static var previews: some View {
        SettingsView()
            .drappulaTheme()
    }
}
