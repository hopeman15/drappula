package com.hellocuriosity.drappula.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.hellocuriosity.drappula.R
import com.hellocuriosity.drappula.ui.components.FeedbackForm
import com.hellocuriosity.drappula.ui.components.FeedbackHeader
import com.hellocuriosity.drappula.ui.components.SlackErrorDialog
import com.hellocuriosity.drappula.ui.feedback.FeedbackViewModel
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(
    viewModel: FeedbackViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val state by viewModel.state.collectAsState()
    val feedbackThanks = stringResource(R.string.feedback_thanks)

    val onSubmit: () -> Unit = {
        viewModel.submit {
            Toast.makeText(context, feedbackThanks, Toast.LENGTH_SHORT).show()
            onNavigateBack()
        }
    }

    state.error?.let {
        SlackErrorDialog(
            onCancel = { viewModel.clearError() },
            onRetry = {
                viewModel.clearError()
                onSubmit()
            },
        )
    }

    Column(
        modifier =
            modifier
                .background(DrappulaTheme.backgroundGradient)
                .safeContentPadding()
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) { focusManager.clearFocus() }
                .testTag(FeedbackTestTags.SCREEN),
    ) {
        FeedbackHeader(onNavigateBack = onNavigateBack)

        FeedbackForm(
            feedback = state.feedback,
            isSubmitEnabled = state.feedback.isComplete && !state.loading,
            onUpdateFeedback = { viewModel.updateFeedback(it) },
            onSubmit = onSubmit,
        )
    }
}

object FeedbackTestTags {
    const val SCREEN = "FEEDBACK_SCREEN"
    const val BACK_BUTTON = "FEEDBACK_BACK"
    const val TITLE = "FEEDBACK_TITLE"
    const val TYPE_PICKER = "FEEDBACK_TYPE"
    const val TITLE_FIELD = "FEEDBACK_TITLE_FIELD"
    const val MESSAGE_FIELD = "FEEDBACK_MESSAGE_FIELD"
    const val SUBMIT_BUTTON = "FEEDBACK_SUBMIT"
}
