import SwiftUI
import shared

struct SoundPlayerView: View {
    let category: shared.Category
    @ObservedObject var viewModel: SoundPlayerViewModel

    private let columns = [
        GridItem(.adaptive(minimum: 120))
    ]

    private var sounds: [Sound] {
        SoundProvider().soundFor(category: category).compactMap { $0 as? Sound }
    }

    var body: some View {
        ScrollView {
            VStack(spacing: 20) {
                Text("Drappula")
                    .font(.largeTitle)
                    .fontWeight(.bold)
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
    }
}
