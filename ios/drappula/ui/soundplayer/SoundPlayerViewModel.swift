import Foundation
import shared

protocol SoundPlayerProtocol {
    func play(sound: Sound)
    func releaseSoundPlayer()
}

protocol SoundSequencerProtocol {
    func play(sounds: [Sound], onComplete: (() -> Void)?)
    func stop()
    func isPlaying() -> Bool
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

class SoundSequencerWrapper: SoundSequencerProtocol {
    private let sequencer: SoundSequencer

    init(sequencer: SoundSequencer) {
        self.sequencer = sequencer
    }

    func play(sounds: [Sound], onComplete: (() -> Void)?) {
        sequencer.play(sounds: sounds, onComplete: onComplete)
    }

    func stop() {
        sequencer.stop()
    }

    func isPlaying() -> Bool {
        return sequencer.isPlaying()
    }
}

struct SoundSequenceModel: Equatable, Identifiable {
    let id: Int64
    let name: String
    let sounds: [Sound]

    static func == (lhs: SoundSequenceModel, rhs: SoundSequenceModel) -> Bool {
        return lhs.id == rhs.id && lhs.name == rhs.name && lhs.sounds.count == rhs.sounds.count
    }
}

@MainActor
class SoundPlayerViewModel: ObservableObject {
    struct State: Equatable {
        var isPlaying: Bool = false
        var sequences: [SoundSequenceModel] = []
    }

    @Published private(set) var state = State()

    private let soundPlayer: SoundPlayerProtocol
    private let soundSequencer: SoundSequencerProtocol
    private let reportHandler: ReportHandler

    init(
        soundPlayer: SoundPlayerProtocol,
        soundSequencer: SoundSequencerProtocol,
        reportHandler: ReportHandler
    ) {
        self.soundPlayer = soundPlayer
        self.soundSequencer = soundSequencer
        self.reportHandler = reportHandler
    }

    func playSound(_ sound: Sound) {
        state = State(isPlaying: true, sequences: state.sequences)
        soundPlayer.play(sound: sound)
        state = State(isPlaying: false, sequences: state.sequences)
        reportHandler.logEvent(event: SoundEvent.Play(sound: sound))
    }

    func playSequence(_ sequence: SoundSequenceModel) {
        state = State(isPlaying: true, sequences: state.sequences)
        soundSequencer.play(sounds: sequence.sounds) { [weak self] in
            DispatchQueue.main.async {
                guard let self = self else { return }
                self.state = State(isPlaying: false, sequences: self.state.sequences)
            }
        }
    }

    func deleteSequence(id: Int64) {
        // Will be wired to repository in Phase 6
    }

    func updateSequences(_ sequences: [SoundSequenceModel]) {
        state = State(isPlaying: state.isPlaying, sequences: sequences)
    }

    deinit {
        soundPlayer.releaseSoundPlayer()
    }

    static func create() -> SoundPlayerViewModel {
        let bundle = NSBundleWrapper()
        let audioPlayer = AVAudioPlayerWrapper()
        let soundPlayer = SoundPlayer(bundle: bundle, audioPlayer: audioPlayer)
        let sequencer = SoundSequencer(soundPlayer: soundPlayer)
        let reportHandler = ReportHandlerFactory.create()
        return SoundPlayerViewModel(
            soundPlayer: SoundPlayerWrapper(soundPlayer: soundPlayer),
            soundSequencer: SoundSequencerWrapper(sequencer: sequencer),
            reportHandler: reportHandler
        )
    }
}
