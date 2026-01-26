import SwiftUI
import shared

struct SoundPlayerView: View {
    let category: shared.Category
    @ObservedObject var viewModel: SoundPlayerViewModel
    @Environment(\.colorScheme) private var colorScheme

    private let columns = [
        GridItem(.adaptive(minimum: 120))
    ]

    private var sounds: [Sound] {
        SoundProvider().soundFor(category: category).compactMap { $0 as? Sound }
    }

    var body: some View {
        let colors = DraculaTheme.colors(for: colorScheme)

        ScrollView {
            VStack(spacing: 20) {
                Text(category.displayName)
                    .font(.custom("Cinzel-Bold", size: 32))
                    .fontWeight(.bold)
                    .foregroundColor(Color(hex: colors.onBackground))
                    .padding(.vertical, 24)

                LazyVGrid(columns: columns, spacing: 8) {
                    ForEach(sounds, id: \.id) { sound in
                        SoundButton(
                            sound: sound,
                            isPlaying: viewModel.state.isPlaying,
                            colors: colors,
                            onTap: { viewModel.playSound(sound) }
                        )
                    }
                }
                .padding(.horizontal, 16)
            }
            .padding()
        }
        .background(DraculaTheme.backgroundGradient(for: colorScheme))
    }
}
