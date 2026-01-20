import SwiftUI
import shared

struct SoundPlayerView: View {
    @ObservedObject var viewModel: SoundPlayerViewModel

    var body: some View {
        VStack(spacing: 20) {
            Text("Drappula")
                .font(.largeTitle)
                .fontWeight(.bold)

            Button {
                viewModel.playSound(Dracula.iAm)
            } label: {
                Text("I Am")
                    .font(.title2)
                    .padding()
                    .frame(maxWidth: .infinity)
                    .background(Color.red)
                    .foregroundColor(.white)
                    .cornerRadius(10)
            }
            .disabled(viewModel.state.isPlaying)

            Button {
                viewModel.playSound(Dracula.dracula)
            } label: {
                Text("Dracula")
                    .font(.title2)
                    .padding()
                    .frame(maxWidth: .infinity)
                    .background(Color.red)
                    .foregroundColor(.white)
                    .cornerRadius(10)
            }
            .disabled(viewModel.state.isPlaying)
        }
        .padding()
    }
}
