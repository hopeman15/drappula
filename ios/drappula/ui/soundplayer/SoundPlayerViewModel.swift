import Foundation
import shared

protocol SoundPlayerProtocol {
    func play(sound: Sound)
    func releaseSoundPlayer()
}

class SoundPlayerWrapper: SoundPlayerProtocol {
    private let soundPlayer: SoundPlayer

    init(soundPlayer: SoundPlayer) {
        self.soundPlayer = soundPlayer
    }

    func play(sound: Sound) {
        soundPlayer.play(sound: sound)
    }

    func releaseSoundPlayer() {
        soundPlayer.release()
    }
}

@MainActor
class SoundPlayerViewModel: ObservableObject {
    struct State: Equatable {
        var isPlaying: Bool = false
    }

    @Published private(set) var state = State()

    private let soundPlayer: SoundPlayerProtocol
    private let reportHandler: ReportHandler

    init(soundPlayer: SoundPlayerProtocol, reportHandler: ReportHandler) {
        self.soundPlayer = soundPlayer
        self.reportHandler = reportHandler
    }

    func playSound(_ sound: Sound) {
        state = State(isPlaying: true)
        soundPlayer.play(sound: sound)
        state = State(isPlaying: false)
        reportHandler.logEvent(event: SoundEvent.Play(sound: sound))
    }

    deinit {
        soundPlayer.releaseSoundPlayer()
    }

    static func create() -> SoundPlayerViewModel {
        let bundle = NSBundleWrapper()
        let audioPlayer = AVAudioPlayerWrapper()
        let soundPlayer = SoundPlayer(bundle: bundle, audioPlayer: audioPlayer)
        let reportHandler = ReportHandlerFactory.create()
        return SoundPlayerViewModel(
            soundPlayer: SoundPlayerWrapper(soundPlayer: soundPlayer),
            reportHandler: reportHandler
        )
    }
}
