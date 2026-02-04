package com.hellocuriosity.drappula.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.hellocuriosity.drappula.R
import com.hellocuriosity.drappula.provider.CategoryProvider
import com.hellocuriosity.drappula.ui.components.AttributionItem
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme

@Composable
fun AttributionScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {},
) {
    val context = LocalContext.current

    Column(
        modifier =
            modifier
                .background(DrappulaTheme.backgroundGradient)
                .safeContentPadding()
                .fillMaxSize()
                .testTag(AttributionTestTags.SCREEN),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp),
        ) {
            IconButton(
                onClick = onNavigateBack,
                modifier = Modifier.testTag(AttributionTestTags.BACK_BUTTON),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
            Text(
                text = stringResource(R.string.attribution_title),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.testTag(AttributionTestTags.TITLE),
            )
        }

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
        ) {
            HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))

            CategoryProvider.all.forEach { attribution ->
                AttributionItem(
                    attribution = attribution,
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, attribution.url.toUri())
                        context.startActivity(intent)
                    },
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f))
            }
        }
    }
}

object AttributionTestTags {
    const val SCREEN = "SCREEN"
    const val TITLE = "TITLE"
    const val BACK_BUTTON = "BACK_BUTTON"
}

@Preview
@Composable
private fun AttributionScreenPreview() {
    AttributionScreen()
}
