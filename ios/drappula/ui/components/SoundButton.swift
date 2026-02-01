import shared
import SwiftUI

struct SoundButton: View {
    let sound: Sound
    let isPlaying: Bool
    let onTap: () -> Void

    @Environment(\.drappulaTheme) private var theme

    var body: some View {
        Button(action: onTap) {
            Text(sound.displayName)
                .font(theme.typography.button)
                .padding(.horizontal, 16)
                .padding(.vertical, 12)
                .frame(maxWidth: .infinity)
                .background(theme.gradients.button)
                .foregroundColor(isPlaying ? theme.colors.onSurface.opacity(0.5) : theme.colors.onSurface)
                .cornerRadius(10)
                .shadow(color: .black.opacity(0.3), radius: 4, x: 0, y: 2)
        }
        .disabled(isPlaying)
    }
}
