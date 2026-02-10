package com.hellocuriosity.drappula.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hellocuriosity.drappula.R
import com.hellocuriosity.drappula.ui.components.SettingsItem
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onNavigateToAttribution: () -> Unit = {},
    isClassicEnabled: Boolean = false,
    onClassicToggle: (Boolean) -> Unit = {},
) {
    Column(
        modifier =
            modifier
                .background(DrappulaTheme.backgroundGradient)
                .safeContentPadding()
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .testTag(SettingsTestTags.SCREEN),
    ) {
        Text(
            text = stringResource(R.string.settings_title),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier =
                Modifier
                    .padding(vertical = 24.dp)
                    .align(Alignment.CenterHorizontally)
                    .testTag(SettingsTestTags.TITLE),
        )

        HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))

        SettingsToggleItem(
            title = stringResource(R.string.settings_classic),
            checked = isClassicEnabled,
            onCheckedChange = onClassicToggle,
            modifier = Modifier.testTag(SettingsTestTags.CLASSIC_TOGGLE),
        )

        HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))

        SettingsItem(
            title = stringResource(R.string.settings_attribution),
            onClick = onNavigateToAttribution,
            modifier = Modifier.testTag(SettingsTestTags.ATTRIBUTION_ITEM),
        )

        HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
    }
}

@Composable
private fun SettingsToggleItem(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f),
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors =
                SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.onBackground,
                    checkedTrackColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                    uncheckedThumbColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    uncheckedTrackColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                ),
        )
    }
}

object SettingsTestTags {
    const val SCREEN = "SCREEN"
    const val TITLE = "TITLE"
    const val CLASSIC_TOGGLE = "CLASSIC_TOGGLE"
    const val ATTRIBUTION_ITEM = "ATTRIBUTION_ITEM"
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}
