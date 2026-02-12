package com.hellocuriosity.drappula.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.hellocuriosity.drappula.R

@Composable
fun SlackErrorDialog(
    onCancel: () -> Unit,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        onDismissRequest = onCancel,
        text = {
            Text(
                text = stringResource(R.string.feedback_error_message),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.testTag(SlackErrorDialogTestTags.MESSAGE),
            )
        },
        dismissButton = {
            TextButton(
                onClick = onCancel,
                modifier = Modifier.testTag(SlackErrorDialogTestTags.CANCEL_BUTTON),
            ) {
                Text(stringResource(R.string.feedback_error_cancel))
            }
        },
        confirmButton = {
            TextButton(
                onClick = onRetry,
                modifier = Modifier.testTag(SlackErrorDialogTestTags.RETRY_BUTTON),
            ) {
                Text(stringResource(R.string.feedback_error_retry))
            }
        },
        modifier = modifier.testTag(SlackErrorDialogTestTags.DIALOG),
    )
}

object SlackErrorDialogTestTags {
    const val DIALOG = "SLACK_ERROR_DIALOG"
    const val MESSAGE = "SLACK_ERROR_MESSAGE"
    const val CANCEL_BUTTON = "SLACK_ERROR_CANCEL"
    const val RETRY_BUTTON = "SLACK_ERROR_RETRY"
}
