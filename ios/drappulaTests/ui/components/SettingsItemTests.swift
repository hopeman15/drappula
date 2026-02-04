import Testing
@testable import Drappula

@MainActor
struct SettingsItemTests {
    @Test
    func settingsItemDisplaysTitle() async {
        let title = "Attribution"
        let theme = DrappulaThemeValues.light
        let item = SettingsItem(title: title, theme: theme)

        #expect(item.title == title)
    }
}
