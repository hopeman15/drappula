import Testing
import SwiftUI
@testable import Drappula

struct ColorHexTests {

    @Test
    func testSixCharacterHex() {
        let color = Color(hex: "2d1b4e")
        let expected = Color(.sRGB, red: 45.0 / 255.0, green: 27.0 / 255.0, blue: 78.0 / 255.0, opacity: 1.0)

        #expect(color == expected)
    }

    @Test
    func testSixCharacterHexWhite() {
        let color = Color(hex: "ffffff")
        let expected = Color(.sRGB, red: 1.0, green: 1.0, blue: 1.0, opacity: 1.0)

        #expect(color == expected)
    }

    @Test
    func testSixCharacterHexBlack() {
        let color = Color(hex: "000000")
        let expected = Color(.sRGB, red: 0.0, green: 0.0, blue: 0.0, opacity: 1.0)

        #expect(color == expected)
    }

    @Test
    func testThreeCharacterHex() {
        let color = Color(hex: "fff")
        let expected = Color(.sRGB, red: 1.0, green: 1.0, blue: 1.0, opacity: 1.0)

        #expect(color == expected)
    }

    @Test
    func testThreeCharacterHexBlack() {
        let color = Color(hex: "000")
        let expected = Color(.sRGB, red: 0.0, green: 0.0, blue: 0.0, opacity: 1.0)

        #expect(color == expected)
    }

    @Test
    func testEightCharacterHexWithAlpha() {
        let color = Color(hex: "80ff0000")
        let expected = Color(.sRGB, red: 1.0, green: 0.0, blue: 0.0, opacity: 128.0 / 255.0)

        #expect(color == expected)
    }

    @Test
    func testHexWithHashPrefix() {
        let color = Color(hex: "#2d1b4e")
        let expected = Color(.sRGB, red: 45.0 / 255.0, green: 27.0 / 255.0, blue: 78.0 / 255.0, opacity: 1.0)

        #expect(color == expected)
    }

    @Test
    func testInvalidHexDefaultsToBlack() {
        let color = Color(hex: "invalid")
        let expected = Color(.sRGB, red: 0.0, green: 0.0, blue: 0.0, opacity: 1.0)

        #expect(color == expected)
    }
}
