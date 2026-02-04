import Testing
@testable import Drappula
import shared

@MainActor
struct SoundButtonTests {
    @Test
    func soundButtonCanBeInitialized() async {
        var tapCount = 0
        let _ = SoundButton(
            sound: Dracula.i,
            isPlaying: false,
            onTap: { tapCount += 1 }
        )

        #expect(tapCount == 0)
    }

    @Test
    func soundButtonAcceptsPlayingState() async {
        let _ = SoundButton(
            sound: Dracula.i,
            isPlaying: true,
            onTap: {}
        )
    }

    @Test
    func soundButtonAcceptsDifferentSounds() async {
        let _ = SoundButton(
            sound: Dracula.dracula,
            isPlaying: false,
            onTap: {}
        )
    }
}
