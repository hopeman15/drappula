import Foundation
import shared

@MainActor
class CreateSequenceViewModel: ObservableObject {
    struct State: Equatable {
        var selectedSounds: [Sound] = []
        var sequenceName: String = ""
        var isSaving: Bool = false
        var savedSuccessfully: Bool = false

        static func == (lhs: State, rhs: State) -> Bool {
            return lhs.sequenceName == rhs.sequenceName
                && lhs.isSaving == rhs.isSaving
                && lhs.savedSuccessfully == rhs.savedSuccessfully
                && lhs.selectedSounds.count == rhs.selectedSounds.count
        }
    }

    @Published private(set) var state = State()

    private let repository: SoundSequenceRepository

    init(repository: SoundSequenceRepository) {
        self.repository = repository
    }

    func addSound(_ sound: Sound) {
        state.selectedSounds.append(sound)
    }

    func removeSound(at index: Int) {
        guard index >= 0 && index < state.selectedSounds.count else { return }
        state.selectedSounds.remove(at: index)
    }

    func setName(_ name: String) {
        state.sequenceName = name
    }

    func save() {
        guard !state.sequenceName.trimmingCharacters(in: .whitespaces).isEmpty,
              !state.selectedSounds.isEmpty else { return }

        state.isSaving = true
        repository.create(
            name: state.sequenceName.trimmingCharacters(in: .whitespaces),
            sounds: state.selectedSounds
        )
        state.isSaving = false
        state.savedSuccessfully = true
    }

    func reset() {
        state = State()
    }
}
