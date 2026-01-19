import SwiftUI
import shared

struct ContentView: View {
    private let soundPlayer: SoundPlayer

    init() {
        let bundle = NSBundleWrapper()
        let audioPlayer = AVAudioPlayerWrapper()
        soundPlayer = SoundPlayer(bundle: bundle, audioPlayer: audioPlayer)
    }

    var body: some View {
        VStack(spacing: 20) {
            Text("Drappula")
                .font(.largeTitle)
                .fontWeight(.bold)

            Button {
                soundPlayer.play(sound: Dracula.iAm)
            } label: {
                Text("I Am")
                    .font(.title2)
                    .padding()
                    .frame(maxWidth: .infinity)
                    .background(Color.red)
                    .foregroundColor(.white)
                    .cornerRadius(10)
            }

            Button {
                soundPlayer.play(sound: Dracula.dracula)
            } label: {
                Text("Dracula")
                    .font(.title2)
                    .padding()
                    .frame(maxWidth: .infinity)
                    .background(Color.red)
                    .foregroundColor(.white)
                    .cornerRadius(10)
            }
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
