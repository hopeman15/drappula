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

class MockSoundSequencer: SoundSequencerProtocol {
    var playCallCount = 0
    var lastPlayedSounds: [Sound] = []
    var stopCallCount = 0
    private var _isPlaying = false

    func play(sounds: [Sound], onComplete: (() -> Void)?) {
        playCallCount += 1
        lastPlayedSounds = sounds
        _isPlaying = true
        onComplete?()
        _isPlaying = false
    }

    func stop() {
        stopCallCount += 1
        _isPlaying = false
    }

    func isPlaying() -> Bool {
        return _isPlaying
    }
}
