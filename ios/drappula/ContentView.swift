import shared
import SwiftUI

struct ContentView: View {
    @StateObject private var viewModel = SoundPlayerViewModel.create()
    @AppStorage("isClassicEnabled") private var isClassicEnabled = false

    var body: some View {
        TabView {
            SoundPlayerView(
                category: shared.Category.dracula,
                viewModel: viewModel
            )
            .tabItem {
                Label("Audio", systemImage: "speaker.wave.2")
            }

            SettingsView(
                isClassicEnabled: $isClassicEnabled
            )
            .tabItem {
                Label("Settings", systemImage: "gear")
            }
        }
        .drappulaTheme(theme: isClassicEnabled ? ClassicTheme.shared : nil)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
