import SwiftUI
import shared

struct SoundButton: View {
    let sound: Sound
    let isPlaying: Bool
    let onTap: () -> Void

    var body: some View {
        Button(action: onTap) {
            Text(sound.displayName)
                .font(.title2)
                .padding(8)
                .frame(maxWidth: .infinity)
                .background(Color.red)
                .foregroundColor(.white)
                .cornerRadius(10)
        }
        .disabled(isPlaying)
    }
}
