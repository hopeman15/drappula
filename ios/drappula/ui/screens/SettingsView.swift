import shared
import SwiftUI

struct SettingsView: View {
    @Environment(\.drappulaTheme) private var theme

    var body: some View {
        NavigationStack {
            ZStack {
                theme.gradients.background
                    .ignoresSafeArea()

                ScrollView {
                    VStack(spacing: 0) {
                        Text("Settings")
                            .font(theme.typography.display)
                            .fontWeight(.bold)
                            .foregroundColor(theme.colors.onBackground)
                            .padding(.vertical, 24)

                        Divider()
                            .background(theme.colors.onBackground.opacity(0.2))

                        NavigationLink(destination: AttributionView()) {
                            SettingsItem(title: "Attribution", theme: theme)
                        }

                        Divider()
                            .background(theme.colors.onBackground.opacity(0.2))
                    }
                }
            }
            .navigationBarHidden(true)
        }
    }
}

struct SettingsView_Previews: PreviewProvider {
    static var previews: some View {
        SettingsView()
            .drappulaTheme()
    }
}
