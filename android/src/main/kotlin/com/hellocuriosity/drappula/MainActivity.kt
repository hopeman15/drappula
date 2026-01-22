package com.hellocuriosity.drappula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hellocuriosity.drappula.models.Category
import com.hellocuriosity.drappula.ui.common.viewModelBuilder
import com.hellocuriosity.drappula.ui.screens.SoundPlayerScreen
import com.hellocuriosity.drappula.ui.soundplayer.SoundPlayerViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: SoundPlayerViewModel by viewModelBuilder {
        SoundPlayerViewModel.viewModel(
            component = (applicationContext as DrappulaApplication).getComponent(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            SoundPlayerScreen(
                category = Category.DRACULA,
                viewModel = viewModel,
            )
        }
    }
}
