import shared
import SwiftUI

struct ConsentView: View {
    let onConsentGiven: (ConsentStateIOS) -> Void

    @Environment(\.drappulaTheme) private var theme
    @State private var analyticsEnabled = false
    @State private var crashReportingEnabled = false
    @Environment(\.openURL) private var openURL

    var body: some View {
        ScrollView {
            VStack(spacing: 16) {
                Spacer().frame(height: 32)

                Text("Your Privacy")
                    .font(theme.typography.display)
                    .foregroundColor(theme.colors.onBackground)
                    .multilineTextAlignment(.center)

                Text(
                    "We respect your privacy. This app can collect data to help us "
                        + "improve your experience. You can choose which types of data "
                        + "collection to allow."
                )
                .font(theme.typography.body)
                .foregroundColor(theme.colors.onBackground)
                .multilineTextAlignment(.center)
                .padding(.horizontal, 16)

                Spacer().frame(height: 16)

                ConsentToggleRow(
                    title: "Analytics & Performance",
                    description: "Help us understand how the app is used and identify "
                        + "performance issues. Collects anonymous usage data.",
                    isOn: $analyticsEnabled,
                    theme: theme
                )

                ConsentToggleRow(
                    title: "Crash Reporting",
                    description: "Help us fix bugs by automatically sending crash reports "
                        + "when something goes wrong.",
                    isOn: $crashReportingEnabled,
                    theme: theme
                )

                Spacer().frame(height: 16)

                Button(
                    action: {
                        onConsentGiven(ConsentStateIOS(analytics: true, crashReporting: true))
                    },
                    label: {
                        Text("Accept All")
                            .font(theme.typography.button)
                            .frame(maxWidth: .infinity)
                            .padding(.vertical, 12)
                    }
                )
                .buttonStyle(.borderedProminent)

                Button(
                    action: {
                        onConsentGiven(ConsentStateIOS(
                            analytics: analyticsEnabled,
                            crashReporting: crashReportingEnabled
                        ))
                    },
                    label: {
                        Text("Continue with Selected")
                            .font(theme.typography.button)
                            .frame(maxWidth: .infinity)
                            .padding(.vertical, 12)
                    }
                )
                .buttonStyle(.bordered)

                Button(
                    action: {
                        onConsentGiven(ConsentStateIOS(analytics: false, crashReporting: false))
                    },
                    label: {
                        Text("Decline All")
                            .font(theme.typography.button)
                            .frame(maxWidth: .infinity)
                            .padding(.vertical, 12)
                    }
                )
                .buttonStyle(.bordered)

                Button(
                    action: {
                        if let url = URL(string: ConsentManagerKt.PRIVACY_POLICY_URL) {
                            openURL(url)
                        }
                    },
                    label: {
                        Text("Privacy Policy")
                            .font(theme.typography.caption)
                    }
                )

                Text("You can change these settings at any time in Settings > Privacy.")
                    .font(theme.typography.caption)
                    .foregroundColor(theme.colors.onBackground.opacity(0.6))
                    .multilineTextAlignment(.center)

                Spacer().frame(height: 16)
            }
            .padding(24)
        }
        .background(theme.gradients.background)
    }
}

private struct ConsentToggleRow: View {
    let title: String
    let description: String
    @Binding var isOn: Bool
    let theme: DrappulaThemeValues

    var body: some View {
        HStack(alignment: .top) {
            VStack(alignment: .leading, spacing: 4) {
                Text(title)
                    .font(theme.typography.title)
                    .foregroundColor(theme.colors.onBackground)
                Text(description)
                    .font(theme.typography.caption)
                    .foregroundColor(theme.colors.onBackground.opacity(0.7))
            }
            Spacer()
            Toggle("", isOn: $isOn)
                .labelsHidden()
        }
        .padding(.horizontal, 16)
    }
}
