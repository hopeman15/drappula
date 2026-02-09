import SwiftUI
import Testing
@testable import Drappula

@MainActor
struct FlowLayoutTests {
    @Test
    func flowLayoutCanBeInitialized() async {
        let layout = FlowLayout()
        #expect(layout.spacing == 8)
    }

    @Test
    func flowLayoutAcceptsCustomSpacing() async {
        let layout = FlowLayout(spacing: 16)
        #expect(layout.spacing == 16)
    }
}
