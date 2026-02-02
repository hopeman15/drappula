package com.hellocuriosity.drappula.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hellocuriosity.drappula.models.Category
import com.hellocuriosity.drappula.provider.SoundProvider
import com.hellocuriosity.drappula.ui.components.SoundButton
import com.hellocuriosity.drappula.ui.soundplayer.SoundPlayerViewModel
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SoundPlayerScreen(
    category: Category,
    viewModel: SoundPlayerViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsState()
    val sounds = SoundProvider().soundFor(category)

    Column(
        modifier =
            modifier
                .background(DrappulaTheme.backgroundGradient)
                .safeContentPadding()
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .testTag(SoundPlayerTestTags.SCREEN),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = category.displayName,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier =
                Modifier
                    .padding(vertical = 24.dp)
                    .testTag(SoundPlayerTestTags.TITLE),
        )

        FlowRow(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .testTag(SoundPlayerTestTags.FLOW_ROW),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            sounds.forEach { sound ->
                SoundButton(
                    sound = sound,
                    isPlaying = state.isPlaying,
                    onClick = { viewModel.playSound(sound) },
                )
            }
        }
    }
}

object SoundPlayerTestTags {
    const val SCREEN = "SoundPlayerScreen"
    const val TITLE = "SoundPlayerTitle"
    const val FLOW_ROW = "SoundPlayerFlowRow"
    private const val SOUND_BUTTON_PREFIX = "SoundButton_"

    fun soundButton(id: String) = "$SOUND_BUTTON_PREFIX$id"
}
