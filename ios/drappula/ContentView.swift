import shared
import SwiftUI

struct ContentView: View {
    @StateObject private var viewModel = SoundPlayerViewModel.create()

    var body: some View {
        SoundPlayerView(
            category: shared.Category.dracula,
            viewModel: viewModel
        )
        .drappulaTheme()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
