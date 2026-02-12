package com.hellocuriosity.drappula.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hellocuriosity.drappula.R
import com.hellocuriosity.drappula.models.Feedback
import com.hellocuriosity.drappula.ui.feedback.toType
import com.hellocuriosity.drappula.ui.feedback.toTypeIdx
import com.hellocuriosity.drappula.ui.screens.FeedbackTestTags

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackTypePicker(
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
