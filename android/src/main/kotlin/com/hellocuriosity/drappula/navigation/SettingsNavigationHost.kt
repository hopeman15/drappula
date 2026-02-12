package com.hellocuriosity.drappula.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.hellocuriosity.drappula.ui.feedback.FeedbackViewModel
import com.hellocuriosity.drappula.ui.screens.AttributionScreen
import com.hellocuriosity.drappula.ui.screens.FeedbackScreen
import com.hellocuriosity.drappula.ui.screens.SettingsScreen

@Composable
fun SettingsNavigationHost(
    modifier: Modifier = Modifier,
    onShowBottomBar: (Boolean) -> Unit = {},
    isClassicEnabled: Boolean = false,
    onClassicToggle: (Boolean) -> Unit = {},
    feedbackViewModel: FeedbackViewModel,
) {
    var destinationIndex by rememberSaveable { mutableIntStateOf(Destination.LIST) }

    LaunchedEffect(destinationIndex) {
        onShowBottomBar(destinationIndex == Destination.LIST)
    }

    AnimatedContent(
        targetState = destinationIndex,
        transitionSpec = {
            if (targetState > initialState) {
                slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
            } else {
                slideInHorizontally { -it } togetherWith slideOutHorizontally { it }
            }
        },
        label = "SettingsNavigation",
    ) { currentDestination ->
        when (currentDestination) {
            Destination.LIST -> {
                SettingsScreen(
                    onNavigateToAttribution = { destinationIndex = Destination.ATTRIBUTION },
                    onNavigateToFeedback = { destinationIndex = Destination.FEEDBACK },
                    isClassicEnabled = isClassicEnabled,
                    onClassicToggle = onClassicToggle,
                    modifier = modifier,
                )
            }

            Destination.ATTRIBUTION -> {
                AttributionScreen(
                    onNavigateBack = { destinationIndex = Destination.LIST },
                    modifier = modifier,
                )
            }

            Destination.FEEDBACK -> {
                FeedbackScreen(
                    viewModel = feedbackViewModel,
                    onNavigateBack = { destinationIndex = Destination.LIST },
                    modifier = modifier,
                )
            }
        }
    }
}

private object Destination {
    const val LIST = 0
    const val ATTRIBUTION = 1
    const val FEEDBACK = 2
}
