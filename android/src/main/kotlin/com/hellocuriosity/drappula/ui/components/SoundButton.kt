package com.hellocuriosity.drappula.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.hellocuriosity.drappula.models.Sound
import com.hellocuriosity.drappula.ui.screens.SoundPlayerTestTags
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme

@Composable
fun SoundButton(
    sound: Sound,
    isPlaying: Boolean,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(10.dp)
    val textColor = MaterialTheme.colorScheme.onSurface

    Box(
        modifier =
            Modifier
                .testTag(SoundPlayerTestTags.soundButton(sound.id))
                .shadow(4.dp, shape)
                .clip(shape)
                .background(DrappulaTheme.buttonGradient)
                .clickable(enabled = !isPlaying, onClick = onClick)
                .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = sound.displayName,
            color = if (isPlaying) textColor.copy(alpha = 0.5f) else textColor,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}
