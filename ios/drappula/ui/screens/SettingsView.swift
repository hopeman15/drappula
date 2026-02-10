import shared
import SwiftUI

struct SettingsView: View {
    @Environment(\.drappulaTheme) private var theme
    @Binding var isClassicEnabled: Bool

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

                        HStack {
                            Text("Classic Film")
                                .font(theme.typography.body)
                                .foregroundColor(theme.colors.onBackground)

                            Spacer()

                            Toggle("", isOn: $isClassicEnabled)
                                .labelsHidden()
                                .tint(theme.colors.onBackground.opacity(0.3))
                        }
                        .padding(.horizontal, 16)
                        .padding(.vertical, 8)

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
        SettingsView(isClassicEnabled: .constant(false))
            .drappulaTheme()
    }
}
