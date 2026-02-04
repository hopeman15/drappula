import Testing
@testable import Drappula

@MainActor
struct SettingsViewTests {
    @Test
    func settingsViewCanBeInitialized() async {
        let _ = SettingsView()
    }

    @Test
    func settingsViewCanBeInitializedWithTheme() async {
        let _ = SettingsView()
            .drappulaTheme()
    }
}
