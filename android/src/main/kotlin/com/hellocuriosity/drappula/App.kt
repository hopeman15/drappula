package com.hellocuriosity.drappula

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hellocuriosity.drappula.SoundPlayer
import com.hellocuriosity.drappula.models.Dracula

@Composable
fun App() {
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer() }
    val soundPlayer = remember { SoundPlayer(context, mediaPlayer) }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    MaterialTheme {
        Column(
            modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .safeContentPadding()
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = "Drappula",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(vertical = 24.dp),
            )

            Button(
                onClick = { soundPlayer.play(Dracula.I_AM) },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            ) {
                Text(
                    text = "I Am",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp),
                )
            }

            Button(
                onClick = { soundPlayer.play(Dracula.DRACULA) },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            ) {
                Text(
                    text = "Dracula",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp),
                )
            }
        }
    }
}
