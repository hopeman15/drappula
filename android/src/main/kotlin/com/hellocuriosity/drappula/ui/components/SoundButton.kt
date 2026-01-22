package com.hellocuriosity.drappula.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hellocuriosity.drappula.models.Sound
import com.hellocuriosity.drappula.ui.screens.SoundPlayerTestTags

@Composable
fun SoundButton(
    sound: Sound,
    isPlaying: Boolean,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier.testTag(SoundPlayerTestTags.soundButton(sound.id)),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        enabled = !isPlaying,
    ) {
        Text(
            text = sound.displayName,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp),
        )
    }
}
