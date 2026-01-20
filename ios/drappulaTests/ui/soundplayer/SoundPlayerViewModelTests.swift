import Testing
@testable import Drappula
import shared

@MainActor
struct SoundPlayerViewModelTests {
    @Test
    func initialStateIsNotPlaying() async {
        let mockPlayer = MockSoundPlayer()
        let viewModel = SoundPlayerViewModel(soundPlayer: mockPlayer)

        #expect(viewModel.state.isPlaying == false)
    }

    @Test
    func playSoundCallsSoundPlayer() async {
        let mockPlayer = MockSoundPlayer()
        let viewModel = SoundPlayerViewModel(soundPlayer: mockPlayer)

        viewModel.playSound(Dracula.iAm)

        #expect(mockPlayer.playCallCount == 1)
        #expect(mockPlayer.lastPlayedSound as? Dracula == Dracula.iAm)
    }

    @Test
    func playSoundWithDraculaCallsSoundPlayer() async {
        let mockPlayer = MockSoundPlayer()
        let viewModel = SoundPlayerViewModel(soundPlayer: mockPlayer)

        viewModel.playSound(Dracula.dracula)

        #expect(mockPlayer.playCallCount == 1)
        #expect(mockPlayer.lastPlayedSound as? Dracula == Dracula.dracula)
    }

    @Test
    func playSoundSetsStateBackToNotPlaying() async {
        let mockPlayer = MockSoundPlayer()
        let viewModel = SoundPlayerViewModel(soundPlayer: mockPlayer)

        viewModel.playSound(Dracula.iAm)

        #expect(viewModel.state.isPlaying == false)
    }
}
