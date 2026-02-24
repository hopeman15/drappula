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
import com.hellocuriosity.drappula.consent.ConsentState
import com.hellocuriosity.drappula.ui.components.SettingsItem
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onNavigateToAttribution: () -> Unit = {},
    onNavigateToFeedback: () -> Unit = {},
    isClassicEnabled: Boolean = false,
    onClassicToggle: (Boolean) -> Unit = {},
    consentState: ConsentState = ConsentState(),
    onConsentChange: (ConsentState) -> Unit = {},
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

        SettingsItem(
            title = stringResource(R.string.settings_feedback),
            onClick = onNavigateToFeedback,
            modifier = Modifier.testTag(SettingsTestTags.FEEDBACK_ITEM),
        )

        HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))

        PrivacySettingsSection(
            consentState = consentState,
            onConsentChange = onConsentChange,
        )
    }
}

@Composable
private fun PrivacySettingsSection(
    consentState: ConsentState,
    onConsentChange: (ConsentState) -> Unit,
) {
    Text(
        text = "Privacy",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier =
            Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .testTag(SettingsTestTags.PRIVACY_HEADER),
    )

    SettingsToggleItem(
        title = "Analytics & Performance",
        checked = consentState.analytics,
        onCheckedChange = { enabled ->
            onConsentChange(consentState.copy(analytics = enabled))
        },
        modifier = Modifier.testTag(SettingsTestTags.ANALYTICS_TOGGLE),
    )

    HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))

    SettingsToggleItem(
        title = "Crash Reporting",
        checked = consentState.crashReporting,
        onCheckedChange = { enabled ->
            onConsentChange(consentState.copy(crashReporting = enabled))
        },
        modifier = Modifier.testTag(SettingsTestTags.CRASH_REPORTING_TOGGLE),
    )

    HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
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
    const val FEEDBACK_ITEM = "FEEDBACK_ITEM"
    const val PRIVACY_HEADER = "PRIVACY_HEADER"
    const val ANALYTICS_TOGGLE = "ANALYTICS_TOGGLE"
    const val CRASH_REPORTING_TOGGLE = "CRASH_REPORTING_TOGGLE"
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen()
}
