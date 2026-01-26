import Testing
@testable import Drappula
import shared

@MainActor
struct SoundButtonTests {
    private let testColors = shared.DraculaTheme.shared.lightColors

    @Test
    func soundButtonCanBeInitialized() async {
        var tapCount = 0
        let _ = SoundButton(
            sound: Dracula.iAm,
            isPlaying: false,
            colors: testColors,
            onTap: { tapCount += 1 }
        )

        #expect(tapCount == 0)
    }

    @Test
    func soundButtonAcceptsPlayingState() async {
        let _ = SoundButton(
            sound: Dracula.iAm,
            isPlaying: true,
            colors: testColors,
            onTap: {}
        )
    }

    @Test
    func soundButtonAcceptsDifferentSounds() async {
        let _ = SoundButton(
            sound: Dracula.dracula,
            isPlaying: false,
            colors: testColors,
            onTap: {}
        )
    }
}
