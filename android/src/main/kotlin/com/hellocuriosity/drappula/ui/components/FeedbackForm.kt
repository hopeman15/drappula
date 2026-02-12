package com.hellocuriosity.drappula.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hellocuriosity.drappula.R
import com.hellocuriosity.drappula.models.Feedback
import com.hellocuriosity.drappula.ui.screens.FeedbackTestTags

@Suppress("TopLevelPropertyNaming")
private const val TITLE_MAX_LENGTH = 30

@Suppress("TopLevelPropertyNaming")
private const val MESSAGE_MAX_LENGTH = 300

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackForm(
    feedback: Feedback,
    isSubmitEnabled: Boolean,
    onUpdateFeedback: (Feedback) -> Unit,
    onSubmit: () -> Unit,
) {
    val colors = feedbackTextFieldColors()

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FeedbackTypePicker(feedback = feedback, onUpdateFeedback = onUpdateFeedback, colors = colors)

        FeedbackTextField(
            value = feedback.title ?: "",
            onValueChange = { if (it.length <= TITLE_MAX_LENGTH) onUpdateFeedback(feedback.copy(title = it)) },
            label = stringResource(R.string.feedback_title_hint),
            counter = "${(feedback.title ?: "").length}/$TITLE_MAX_LENGTH",
            colors = colors,
            modifier = Modifier.testTag(FeedbackTestTags.TITLE_FIELD),
        )

        FeedbackTextField(
            value = feedback.message ?: "",
            onValueChange = { if (it.length <= MESSAGE_MAX_LENGTH) onUpdateFeedback(feedback.copy(message = it)) },
            label = stringResource(R.string.feedback_message_hint),
            counter = "${(feedback.message ?: "").length}/$MESSAGE_MAX_LENGTH",
            colors = colors,
            modifier =
                Modifier
                    .defaultMinSize(minHeight = 150.dp)
                    .testTag(FeedbackTestTags.MESSAGE_FIELD),
        )

        FeedbackSubmitButton(enabled = isSubmitEnabled, onClick = onSubmit)
    }
}
