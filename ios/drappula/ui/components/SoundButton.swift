import SwiftUI
import shared

struct SoundButton: View {
    let sound: Sound
    let isPlaying: Bool
    let colors: ThemeColors
    let onTap: () -> Void

    var body: some View {
        let textColor = Color(hex: colors.onSurface)
        let gradient = LinearGradient(
            colors: [
                Color(hex: colors.gradientStart),
                Color(hex: colors.gradientEnd)
            ],
            startPoint: .top,
            endPoint: .bottom
        )

        Button(action: onTap) {
            Text(sound.displayName)
                .font(.title2)
                .padding(.horizontal, 16)
                .padding(.vertical, 12)
                .frame(maxWidth: .infinity)
                .background(gradient)
                .foregroundColor(isPlaying ? textColor.opacity(0.5) : textColor)
                .cornerRadius(10)
                .shadow(color: .black.opacity(0.3), radius: 4, x: 0, y: 2)
        }
        .disabled(isPlaying)
    }
}
