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
                    VStack {
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

                        NavigationLink(destination: FeedbackView()) {
                            SettingsItem(title: "Submit Feedback", theme: theme)
                        }

                        Divider()
                            .background(theme.colors.onBackground.opacity(0.2))

                        Text("Privacy")
                            .font(theme.typography.title)
                            .fontWeight(.bold)
                            .foregroundColor(theme.colors.onBackground)
                            .frame(maxWidth: .infinity, alignment: .leading)
                            .padding(.horizontal, 16)
                            .padding(.vertical, 8)

                        HStack {
                            Text("Analytics & Performance")
                                .font(theme.typography.body)
                                .foregroundColor(theme.colors.onBackground)

                            Spacer()

                            Toggle("", isOn: Binding(
                                get: { ConsentManagerIOS.shared.consentState.analytics },
                                set: { enabled in
                                    let current = ConsentManagerIOS.shared.consentState
                                    ConsentManagerIOS.shared.updateConsent(
                                        ConsentStateIOS(
                                            analytics: enabled,
                                            crashReporting: current.crashReporting
                                        )
                                    )
                                }
                            ))
                            .labelsHidden()
                            .tint(theme.colors.onBackground.opacity(0.3))
                        }
                        .padding(.horizontal, 16)
                        .padding(.vertical, 8)

                        Divider()
                            .background(theme.colors.onBackground.opacity(0.2))

                        HStack {
                            Text("Crash Reporting")
                                .font(theme.typography.body)
                                .foregroundColor(theme.colors.onBackground)

                            Spacer()

                            Toggle("", isOn: Binding(
                                get: { ConsentManagerIOS.shared.consentState.crashReporting },
                                set: { enabled in
                                    let current = ConsentManagerIOS.shared.consentState
                                    ConsentManagerIOS.shared.updateConsent(
                                        ConsentStateIOS(
                                            analytics: current.analytics,
                                            crashReporting: enabled
                                        )
                                    )
                                }
                            ))
                            .labelsHidden()
                            .tint(theme.colors.onBackground.opacity(0.3))
                        }
                        .padding(.horizontal, 16)
                        .padding(.vertical, 8)

                        Divider()
                            .background(theme.colors.onBackground.opacity(0.2))
                    }
                    .padding()
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
