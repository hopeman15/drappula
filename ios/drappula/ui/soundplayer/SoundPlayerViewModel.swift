import Foundation
import shared

protocol SoundPlayerProtocol {
    func play(sound: Sound) throws
    func releaseSoundPlayer()
}

class SoundPlayerWrapper: SoundPlayerProtocol {
    private let soundPlayer: SoundPlayer

    init(soundPlayer: SoundPlayer) {
        self.soundPlayer = soundPlayer
    }

    func play(sound: Sound) throws {
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
        var error: Error?

        static func == (lhs: State, rhs: State) -> Bool {
            lhs.isPlaying == rhs.isPlaying &&
            lhs.error?.localizedDescription == rhs.error?.localizedDescription
        }
    }

    @Published private(set) var state = State()

    private let soundPlayer: SoundPlayerProtocol
    private let reportHandler: ReportHandler

    init(soundPlayer: SoundPlayerProtocol, reportHandler: ReportHandler) {
        self.soundPlayer = soundPlayer
        self.reportHandler = reportHandler
    }

    func playSound(_ sound: Sound) {
        do {
            state = State(isPlaying: true)
            try soundPlayer.play(sound: sound)
            state = State(isPlaying: false)
            reportHandler.logEvent(event: SoundEvent.Play(sound: sound))
        } catch {
            state = State(isPlaying: false, error: error)
            reportHandler.reportException(exception: KotlinThrowable(message: error.localizedDescription))
        }
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
