package com.hellocuriosity.drappula.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.hellocuriosity.drappula.R
import com.hellocuriosity.drappula.models.Feedback
import com.hellocuriosity.drappula.ui.components.SlackErrorDialog
import com.hellocuriosity.drappula.ui.feedback.FeedbackViewModel
import com.hellocuriosity.drappula.ui.feedback.toType
import com.hellocuriosity.drappula.ui.feedback.toTypeIdx
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme

@Suppress("TopLevelPropertyNaming")
private const val TITLE_MAX_LENGTH = 30

@Suppress("TopLevelPropertyNaming")
private const val MESSAGE_MAX_LENGTH = 300

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

@Composable
private fun FeedbackHeader(onNavigateBack: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp),
    ) {
        IconButton(
            onClick = onNavigateBack,
            modifier = Modifier.testTag(FeedbackTestTags.BACK_BUTTON),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }
        Text(
            text = stringResource(R.string.feedback_title),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.testTag(FeedbackTestTags.TITLE),
        )
    }
}

@Composable
private fun feedbackTextFieldColors() =
    OutlinedTextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.onBackground,
        unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
        focusedBorderColor = MaterialTheme.colorScheme.onBackground,
        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        focusedLabelColor = MaterialTheme.colorScheme.onBackground,
        unfocusedLabelColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        cursorColor = MaterialTheme.colorScheme.onBackground,
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedbackForm(
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

@Composable
private fun FeedbackTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    counter: String,
    colors: TextFieldColors,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        supportingText = { Text(counter, color = MaterialTheme.colorScheme.onBackground) },
        keyboardOptions =
            KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
            ),
        colors = colors,
        modifier =
            modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
    )
}

@Composable
private fun FeedbackSubmitButton(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedbackTypePicker(
    feedback: Feedback,
    onUpdateFeedback: (Feedback) -> Unit,
    colors: TextFieldColors,
) {
    val feedbackTypes =
        listOf(
            R.string.feedback_type_enhancement,
            R.string.feedback_type_feature,
            R.string.feedback_type_fix,
            R.string.feedback_type_other,
        )

    var expanded by remember { mutableStateOf(false) }
    val selectedTypeIdx =
        feedback.type.value
            .toTypeIdx()

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .testTag(FeedbackTestTags.TYPE_PICKER),
    ) {
        OutlinedTextField(
            value = stringResource(feedbackTypes[selectedTypeIdx]),
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = colors,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            feedbackTypes.forEachIndexed { index, typeResId ->
                DropdownMenuItem(
                    text = { Text(stringResource(typeResId)) },
                    onClick = {
                        onUpdateFeedback(feedback.copy(type = index.toType()))
                        expanded = false
                    },
                )
            }
        }
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
