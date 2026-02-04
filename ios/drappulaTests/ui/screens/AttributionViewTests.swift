import Testing
@testable import Drappula
import shared

@MainActor
struct AttributionViewTests {
    @Test
    func attributionViewCanBeInitialized() async {
        let _ = AttributionView()
    }

    @Test
    func attributionViewCanBeInitializedWithTheme() async {
        let _ = AttributionView()
            .drappulaTheme()
    }

    @Test
    func attributionRowCanBeInitialized() async {
        let theme = DrappulaThemeValues.light
        let attribution = CategoryProvider().all.first!
        var tapped = false

        let _ = AttributionRow(
            attribution: attribution,
            theme: theme,
            onTap: { tapped = true }
        )

        #expect(tapped == false)
    }

    @Test
    func attributionRowDisplaysAttributionTitle() async {
        let theme = DrappulaThemeValues.light
        let attribution = CategoryProvider().all.first!

        let row = AttributionRow(
            attribution: attribution,
            theme: theme,
            onTap: {}
        )

        #expect(row.attribution.title == attribution.title)
    }
}
