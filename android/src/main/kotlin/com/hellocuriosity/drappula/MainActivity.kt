package com.hellocuriosity.drappula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hellocuriosity.drappula.ui.common.viewModelBuilder
import com.hellocuriosity.drappula.ui.screens.DashboardScreen
import com.hellocuriosity.drappula.ui.soundplayer.SoundPlayerViewModel
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme

class MainActivity : ComponentActivity() {
    private val viewModel: SoundPlayerViewModel by viewModelBuilder {
        SoundPlayerViewModel.viewModel(
            component = (applicationContext as DrappulaApplication).getComponent(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            DrappulaTheme {
                DashboardScreen(
                    soundPlayerViewModel = viewModel,
                )
            }
        }
    }
}
