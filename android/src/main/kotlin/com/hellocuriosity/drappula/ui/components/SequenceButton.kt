package com.hellocuriosity.drappula.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hellocuriosity.drappula.models.SoundSequence
import com.hellocuriosity.drappula.ui.screens.SoundPlayerTestTags

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SequenceButton(
    sequence: SoundSequence,
    isPlaying: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
) {
    val shape = RoundedCornerShape(10.dp)
    val containerColor = MaterialTheme.colorScheme.tertiary
    val textColor = MaterialTheme.colorScheme.onTertiary

    Box(
        modifier =
            Modifier
                .testTag(SoundPlayerTestTags.sequenceButton(sequence.id))
                .shadow(4.dp, shape)
                .clip(shape)
                .background(containerColor)
                .combinedClickable(
                    enabled = !isPlaying,
                    onClick = onClick,
                    onLongClick = onLongClick,
                ).padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = sequence.name,
            color = if (isPlaying) textColor.copy(alpha = 0.5f) else textColor,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}
