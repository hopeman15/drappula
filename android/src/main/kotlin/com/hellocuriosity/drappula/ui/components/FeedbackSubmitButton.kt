package com.hellocuriosity.drappula.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hellocuriosity.drappula.R
import com.hellocuriosity.drappula.ui.screens.FeedbackTestTags

@Composable
fun FeedbackSubmitButton(
    enabled: Boolean,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors =
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                disabledContentColor = MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
            ),
        modifier =
            Modifier
                .padding(top = 40.dp)
                .testTag(FeedbackTestTags.SUBMIT_BUTTON),
    ) {
        Text(stringResource(R.string.feedback_submit_btn))
    }
}
