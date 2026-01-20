import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var viewModel = SoundPlayerViewModel.create()

    var body: some View {
        SoundPlayerView(viewModel: viewModel)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
