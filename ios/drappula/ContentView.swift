import shared
import SwiftUI

struct ContentView: View {
    @StateObject private var viewModel = SoundPlayerViewModel.create()
    @AppStorage("isClassicEnabled") private var isClassicEnabled = false
    @State private var hasResponded = ConsentManagerIOS.shared.hasUserResponded()

    var body: some View {
        Group {
            if hasResponded {
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
            } else {
                ConsentView { state in
                    ConsentManagerIOS.shared.updateConsent(state)
                    hasResponded = true
                }
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
