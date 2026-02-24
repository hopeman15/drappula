package com.hellocuriosity.drappula.ui.consent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hellocuriosity.drappula.BuildConfig
import com.hellocuriosity.drappula.consent.ConsentState
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme

@Composable
fun ConsentScreen(onConsentGiven: (ConsentState) -> Unit) {
    var analyticsEnabled by remember { mutableStateOf(false) }
    var crashReportingEnabled by remember { mutableStateOf(false) }

    Column(
        modifier =
            Modifier
                .background(DrappulaTheme.backgroundGradient)
                .safeContentPadding()
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ConsentHeader()

        ConsentToggles(
            analyticsEnabled = analyticsEnabled,
            onAnalyticsChange = { analyticsEnabled = it },
            crashReportingEnabled = crashReportingEnabled,
            onCrashReportingChange = { crashReportingEnabled = it },
        )

        ConsentButtons(
            onAcceptAll = { onConsentGiven(ConsentState(analytics = true, crashReporting = true)) },
            onContinueSelected = {
                onConsentGiven(
                    ConsentState(analytics = analyticsEnabled, crashReporting = crashReportingEnabled),
                )
            },
            onDeclineAll = { onConsentGiven(ConsentState(analytics = false, crashReporting = false)) },
        )

        ConsentFooter()
    }
}

@Composable
private fun ConsentHeader() {
    Spacer(modifier = Modifier.height(32.dp))

    Text(
        text = "Your Privacy",
        style = MaterialTheme.typography.displayLarge,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Center,
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text =
            "We respect your privacy. This app can collect data to help us " +
                "improve your experience. You can choose which types of data collection to allow.",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Center,
    )

    Spacer(modifier = Modifier.height(32.dp))
}

@Composable
private fun ConsentToggles(
    analyticsEnabled: Boolean,
    onAnalyticsChange: (Boolean) -> Unit,
    crashReportingEnabled: Boolean,
    onCrashReportingChange: (Boolean) -> Unit,
) {
    ConsentToggleRow(
        title = "Analytics & Performance",
        description =
            "Help us understand how the app is used and identify performance issues. " +
                "Collects anonymous usage data.",
        checked = analyticsEnabled,
        onCheckedChange = onAnalyticsChange,
    )

    Spacer(modifier = Modifier.height(16.dp))

    ConsentToggleRow(
        title = "Crash Reporting",
        description =
            "Help us fix bugs by automatically sending crash reports when " +
                "something goes wrong.",
        checked = crashReportingEnabled,
        onCheckedChange = onCrashReportingChange,
    )

    Spacer(modifier = Modifier.height(32.dp))
}

@Composable
private fun ConsentButtons(
    onAcceptAll: () -> Unit,
    onContinueSelected: () -> Unit,
    onDeclineAll: () -> Unit,
) {
    Button(
        onClick = onAcceptAll,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = "Accept All")
    }

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedButton(
        onClick = onContinueSelected,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = "Continue with Selected")
    }

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedButton(
        onClick = onDeclineAll,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = "Decline All")
    }

    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun ConsentFooter() {
    val uriHandler = LocalUriHandler.current

    TextButton(
        onClick = { uriHandler.openUri(BuildConfig.PRIVACY_POLICY_URL) },
    ) {
        Text(
            text = "Privacy Policy",
            style = MaterialTheme.typography.bodySmall,
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "You can change these settings at any time in Settings > Privacy.",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun ConsentToggleRow(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(start = 16.dp),
        )
    }
}
