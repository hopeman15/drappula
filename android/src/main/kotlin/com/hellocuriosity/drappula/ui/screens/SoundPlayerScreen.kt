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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hellocuriosity.drappula.models.Category
import com.hellocuriosity.drappula.models.SoundSequence
import com.hellocuriosity.drappula.providers.SoundProvider
import com.hellocuriosity.drappula.ui.components.DeleteSequenceDialog
import com.hellocuriosity.drappula.ui.components.SequenceButton
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
    var sequenceToDelete by remember { mutableStateOf<SoundSequence?>(null) }

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
            text = category.title,
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

        if (state.sequences.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "My Sequences",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier =
                    Modifier
                        .testTag(SoundPlayerTestTags.SEQUENCES_TITLE),
            )

            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .testTag(SoundPlayerTestTags.SEQUENCES_FLOW_ROW),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                state.sequences.forEach { sequence ->
                    SequenceButton(
                        sequence = sequence,
                        isPlaying = state.isPlaying,
                        onClick = { viewModel.playSequence(sequence) },
                        onLongClick = { sequenceToDelete = sequence },
                    )
                }
            }
        }
    }

    sequenceToDelete?.let { sequence ->
        DeleteSequenceDialog(
            sequence = sequence,
            onConfirm = {
                viewModel.deleteSequence(sequence.id)
                sequenceToDelete = null
            },
            onDismiss = { sequenceToDelete = null },
        )
    }
}

object SoundPlayerTestTags {
    const val SCREEN = "SoundPlayerScreen"
    const val TITLE = "SoundPlayerTitle"
    const val FLOW_ROW = "SoundPlayerFlowRow"
    const val SEQUENCES_TITLE = "SoundPlayerSequencesTitle"
    const val SEQUENCES_FLOW_ROW = "SoundPlayerSequencesFlowRow"
    const val DELETE_DIALOG_TITLE = "DeleteSequenceDialogTitle"
    const val DELETE_DIALOG_TEXT = "DeleteSequenceDialogText"
    const val DELETE_DIALOG_CONFIRM = "DeleteSequenceDialogConfirm"
    const val DELETE_DIALOG_DISMISS = "DeleteSequenceDialogDismiss"
    private const val SOUND_BUTTON_PREFIX = "SoundButton_"
    private const val SEQUENCE_BUTTON_PREFIX = "SequenceButton_"

    fun soundButton(id: String) = "$SOUND_BUTTON_PREFIX$id"

    fun sequenceButton(id: Long) = "$SEQUENCE_BUTTON_PREFIX$id"
}
