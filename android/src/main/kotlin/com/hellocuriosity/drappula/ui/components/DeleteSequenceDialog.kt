package com.hellocuriosity.drappula.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.hellocuriosity.drappula.models.SoundSequence
import com.hellocuriosity.drappula.ui.screens.SoundPlayerTestTags

@Composable
fun DeleteSequenceDialog(
    sequence: SoundSequence,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Delete Sequence",
                modifier = Modifier.testTag(SoundPlayerTestTags.DELETE_DIALOG_TITLE),
            )
        },
        text = {
            Text(
                text = "Delete \"${sequence.name}\"?",
                modifier = Modifier.testTag(SoundPlayerTestTags.DELETE_DIALOG_TEXT),
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                modifier = Modifier.testTag(SoundPlayerTestTags.DELETE_DIALOG_CONFIRM),
            ) {
                Text("Delete", color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.testTag(SoundPlayerTestTags.DELETE_DIALOG_DISMISS),
            ) {
                Text("Cancel")
            }
        },
    )
}
