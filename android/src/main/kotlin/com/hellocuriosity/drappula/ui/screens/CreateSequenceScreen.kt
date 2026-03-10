package com.hellocuriosity.drappula.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hellocuriosity.drappula.models.Dracula
import com.hellocuriosity.drappula.ui.components.SelectedSoundChip
import com.hellocuriosity.drappula.ui.components.SoundButton
import com.hellocuriosity.drappula.ui.createsequence.CreateSequenceViewModel
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateSequenceScreen(
    viewModel: CreateSequenceViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.savedSuccessfully) {
        if (state.savedSuccessfully) {
            viewModel.reset()
            onBack()
        }
    }

    Column(
        modifier =
            modifier
                .background(DrappulaTheme.backgroundGradient)
                .safeContentPadding()
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .testTag(CreateSequenceTestTags.SCREEN),
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier.testTag(CreateSequenceTestTags.BACK_BUTTON),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }

        Text(
            text = "Create Sequence",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier =
                Modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .testTag(CreateSequenceTestTags.TITLE),
        )

        if (state.selectedSounds.isNotEmpty()) {
            Text(
                text = state.sequenceName,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .testTag(CreateSequenceTestTags.SEQUENCE_NAME),
            )

            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .testTag(CreateSequenceTestTags.SELECTED_SOUNDS_ROW),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                state.selectedSounds.forEachIndexed { index, sound ->
                    SelectedSoundChip(
                        sound = sound,
                        onRemove = { viewModel.removeSound(index) },
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(
            text = "Tap to add sounds",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        FlowRow(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .testTag(CreateSequenceTestTags.SOUND_BUTTONS_ROW),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Dracula.entries.forEach { sound ->
                SoundButton(
                    sound = sound,
                    isPlaying = false,
                    onClick = { viewModel.addSound(sound) },
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = viewModel::save,
            enabled = state.selectedSounds.isNotEmpty() && !state.isSaving,
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .testTag(CreateSequenceTestTags.SAVE_BUTTON),
        ) {
            Text(if (state.isSaving) "Saving..." else "Save")
        }
    }
}

object CreateSequenceTestTags {
    const val SCREEN = "CreateSequenceScreen"
    const val BACK_BUTTON = "CreateSequenceBackButton"
    const val TITLE = "CreateSequenceTitle"
    const val SEQUENCE_NAME = "CreateSequenceSequenceName"
    const val SELECTED_SOUNDS_ROW = "CreateSequenceSelectedSoundsRow"
    const val SOUND_BUTTONS_ROW = "CreateSequenceSoundButtonsRow"
    const val SAVE_BUTTON = "CreateSequenceSaveButton"
}
