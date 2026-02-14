import shared
import SwiftUI

struct ContentView: View {
    @StateObject private var viewModel = SoundPlayerViewModel.create()
    @State private var isClassicEnabled = false

    private let preferenceProvider = PreferenceProvider()

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
        .onAppear {
            isClassicEnabled = preferenceProvider.isClassicEnabled
        }
        .onChange(of: isClassicEnabled) { newValue in
            preferenceProvider.isClassicEnabled = newValue
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
