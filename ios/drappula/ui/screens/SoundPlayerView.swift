import shared
import SwiftUI

struct SoundPlayerView: View {
    let category: shared.Category
    @ObservedObject var viewModel: SoundPlayerViewModel
    @Environment(\.drappulaTheme) private var theme

    private let columns = [
        GridItem(.adaptive(minimum: 120))
    ]

    private var sounds: [Sound] {
        SoundProvider().soundFor(category: category).compactMap { $0 as? Sound }
    }

    var body: some View {
        ScrollView {
            VStack(spacing: 20) {
                Text(category.displayName)
                    .font(theme.typography.display)
                    .fontWeight(.bold)
                    .foregroundColor(theme.colors.onBackground)
                    .padding(.vertical, 24)

                LazyVGrid(columns: columns, spacing: 8) {
                    ForEach(sounds, id: \.id) { sound in
                        SoundButton(
                            sound: sound,
                            isPlaying: viewModel.state.isPlaying,
                            onTap: { viewModel.playSound(sound) }
                        )
                    }
                }
                .padding(.horizontal, 16)
            }
            .padding()
        }
        .background(theme.gradients.background)
    }
}
