import shared
import SwiftUI

struct AttributionView: View {
    @Environment(\.drappulaTheme) private var theme
    @Environment(\.dismiss) private var dismiss
    @Environment(\.openURL) private var openURL

    private let attributions = CategoryProvider().all

    var body: some View {
        ZStack {
            theme.gradients.background
                .ignoresSafeArea()

            VStack(alignment: .leading, spacing: 0) {
                HStack {
                    Button {
                        dismiss()
                    } label: {
                        Image(systemName: "arrow.left")
                            .foregroundColor(theme.colors.onBackground)
                            .padding(8)
                    }

                    Text("Attribution")
                        .font(theme.typography.headline)
                        .fontWeight(.bold)
                        .foregroundColor(theme.colors.onBackground)

                    Spacer()
                }
                .padding(.vertical, 8)
                .padding(.horizontal, 8)

                ScrollView {
                    VStack(spacing: 0) {
                        Divider()
                            .background(theme.colors.onBackground.opacity(0.2))

                        ForEach(attributions, id: \.title) { attribution in
                            AttributionRow(
                                attribution: attribution,
                                theme: theme,
                                onTap: {
                                    if let url = URL(string: attribution.url) {
                                        openURL(url)
                                    }
                                }
                            )

                            Divider()
                                .background(theme.colors.onBackground.opacity(0.2))
                        }
                    }
                }
            }
        }
        .navigationBarHidden(true)
        .toolbar(.hidden, for: .tabBar)
    }
}

struct AttributionRow: View {
    let attribution: shared.Category
    let theme: DrappulaThemeValues
    let onTap: () -> Void

    var body: some View {
        Button(action: onTap) {
            HStack {
                VStack(alignment: .leading, spacing: 2) {
                    Text(attribution.title)
                        .font(theme.typography.body)
                        .foregroundColor(theme.colors.onBackground)

                    Text(attribution.license)
                        .font(theme.typography.caption)
                        .foregroundColor(theme.colors.onBackground.opacity(0.6))
                }

                Spacer()

                Image(systemName: "arrow.up.right.square")
                    .foregroundColor(theme.colors.onBackground.opacity(0.5))
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 12)
            .contentShape(Rectangle())
        }
        .buttonStyle(PlainButtonStyle())
    }
}

struct AttributionView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationStack {
            AttributionView()
        }
        .drappulaTheme()
    }
}
