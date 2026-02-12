package com.hellocuriosity.drappula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hellocuriosity.drappula.themes.ClassicTheme
import com.hellocuriosity.drappula.themes.DraculaTheme
import com.hellocuriosity.drappula.ui.common.viewModelBuilder
import com.hellocuriosity.drappula.ui.feedback.FeedbackViewModel
import com.hellocuriosity.drappula.ui.screens.DashboardScreen
import com.hellocuriosity.drappula.ui.soundplayer.SoundPlayerViewModel
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme

class MainActivity : ComponentActivity() {
    private val component by lazy {
        (applicationContext as DrappulaApplication).getComponent()
    }

    private val viewModel: SoundPlayerViewModel by viewModelBuilder {
        SoundPlayerViewModel.viewModel(component = component)
    }

    private val feedbackViewModel: FeedbackViewModel by viewModelBuilder {
        FeedbackViewModel.viewModel(component = component)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val preferenceProvider = component.preferenceProvider
            var isClassicEnabled by remember {
                mutableStateOf(preferenceProvider.isClassicEnabled)
            }
            val theme = if (isClassicEnabled) ClassicTheme else DraculaTheme

            DrappulaTheme(theme = theme) {
                DashboardScreen(
                    soundPlayerViewModel = viewModel,
                    feedbackViewModel = feedbackViewModel,
                    isClassicEnabled = isClassicEnabled,
                    onClassicToggle = {
                        isClassicEnabled = it
                        preferenceProvider.isClassicEnabled = it
                    },
                )
            }
        }
    }
}
