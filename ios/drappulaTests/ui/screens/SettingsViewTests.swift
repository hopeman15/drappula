import SwiftUI
import Testing
@testable import Drappula

@MainActor
struct SettingsViewTests {
    @Test
    func settingsViewCanBeInitialized() async {
        let _ = SettingsView(isClassicEnabled: .constant(false))
    }

    @Test
    func settingsViewCanBeInitializedWithTheme() async {
        let _ = SettingsView(isClassicEnabled: .constant(false))
            .drappulaTheme()
    }

    @Test
    func settingsViewCanBeInitializedWithClassicEnabled() async {
        let _ = SettingsView(isClassicEnabled: .constant(true))
            .drappulaTheme()
    }
}
