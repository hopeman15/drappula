package com.hellocuriosity.drappula.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.hellocuriosity.drappula.R
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier =
            modifier
                .background(DrappulaTheme.backgroundGradient)
                .safeContentPadding()
                .fillMaxSize()
                .testTag(SettingsTestTags.SCREEN),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.settings_title),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.testTag(SettingsTestTags.TITLE),
        )
    }
}

object SettingsTestTags {
    const val SCREEN = "SettingsScreen"
    const val TITLE = "SettingsTitle"
}
