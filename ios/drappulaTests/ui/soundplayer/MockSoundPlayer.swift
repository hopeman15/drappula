import shared
@testable import Drappula

class MockSoundPlayer: SoundPlayerProtocol {
    var playCallCount = 0
    var lastPlayedSound: Sound?
    var releaseCallCount = 0
    var errorToThrow: Error?

    func play(sound: Sound) throws {
        playCallCount += 1
        lastPlayedSound = sound
        if let errorToThrow { throw errorToThrow }
    }

    func releaseSoundPlayer() {
        releaseCallCount += 1
    }
}
