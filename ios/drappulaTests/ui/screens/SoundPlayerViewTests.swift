import Testing
@testable import Drappula
import shared

@MainActor
struct SoundPlayerViewTests {
    @Test
    func soundPlayerViewCanBeInitializedWithDraculaCategory() async {
        let mockPlayer = MockSoundPlayer()
        let viewModel = SoundPlayerViewModel(soundPlayer: mockPlayer)

        let _ = SoundPlayerView(
            category: shared.Category.dracula,
            viewModel: viewModel
        )
    }

    @Test
    func soundPlayerViewUsesProvidedViewModel() async {
        let mockPlayer = MockSoundPlayer()
        let viewModel = SoundPlayerViewModel(soundPlayer: mockPlayer)

        let _ = SoundPlayerView(
            category: shared.Category.dracula,
            viewModel: viewModel
        )

        #expect(viewModel.state.isPlaying == false)
    }

    @Test
    func soundProviderReturnsSoundsForDraculaCategory() async {
        let sounds = SoundProvider().soundFor(category: .dracula)

        #expect(sounds.count > 0)
    }
}
