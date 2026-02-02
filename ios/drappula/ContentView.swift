import shared
import SwiftUI

struct ContentView: View {
    @StateObject private var viewModel = SoundPlayerViewModel.create()

    var body: some View {
        TabView {
            SoundPlayerView(
                category: shared.Category.dracula,
                viewModel: viewModel
            )
            .tabItem {
                Label("Audio", systemImage: "speaker.wave.2")
            }

            SettingsView()
            .tabItem {
                Label("Settings", systemImage: "gear")
            }
        }
        .drappulaTheme()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
