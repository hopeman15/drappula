import shared
@testable import Drappula

class MockSoundPlayer: SoundPlayerProtocol {
    var playCallCount = 0
    var lastPlayedSound: Sound?
    var releaseCallCount = 0

    func play(sound: Sound) {
        playCallCount += 1
        lastPlayedSound = sound
    }

    func releaseSoundPlayer() {
        releaseCallCount += 1
    }
}
