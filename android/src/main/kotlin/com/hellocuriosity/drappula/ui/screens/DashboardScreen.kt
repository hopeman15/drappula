package com.hellocuriosity.drappula.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.hellocuriosity.drappula.models.Category
import com.hellocuriosity.drappula.navigation.SettingsNavigationHost
import com.hellocuriosity.drappula.navigation.Tab
import com.hellocuriosity.drappula.ui.feedback.FeedbackViewModel
import com.hellocuriosity.drappula.ui.soundplayer.SoundPlayerViewModel
import com.hellocuriosity.drappula.ui.theme.DrappulaTheme
import com.hellocuriosity.drappula.ui.theme.toColor

@Composable
fun DashboardScreen(
    soundPlayerViewModel: SoundPlayerViewModel,
    feedbackViewModel: FeedbackViewModel,
    isClassicEnabled: Boolean = false,
    onClassicToggle: (Boolean) -> Unit = {},
) {
    var selectedTab by rememberSaveable { mutableStateOf(Tab.AUDIO) }
    var showBottomBar by rememberSaveable { mutableStateOf(true) }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(DrappulaTheme.backgroundGradient),
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = {
                if (showBottomBar) {
                    DashboardNavigationBar(
                        selectedTab = selectedTab,
                        onTabSelected = { selectedTab = it },
                    )
                }
            },
        ) { paddingValues ->
            when (selectedTab) {
                Tab.AUDIO -> {
                    SoundPlayerScreen(
                        category = Category.DRACULA,
                        viewModel = soundPlayerViewModel,
                        modifier = Modifier.padding(paddingValues),
                    )
                }

                Tab.SETTINGS -> {
                    SettingsNavigationHost(
                        modifier = Modifier.padding(paddingValues),
                        onShowBottomBar = { showBottomBar = it },
                        isClassicEnabled = isClassicEnabled,
                        onClassicToggle = onClassicToggle,
                        feedbackViewModel = feedbackViewModel,
                    )
                }
            }
        }
    }
}

@Composable
private fun DashboardNavigationBar(
    selectedTab: Tab,
    onTabSelected: (Tab) -> Unit,
) {
    NavigationBar(
        containerColor = Color.Transparent,
        modifier = Modifier.testTag(DashboardScreenTestTags.NAVIGATION_BAR),
    ) {
        Tab.entries.forEach { tab ->
            val title = stringResource(tab.titleRes)
            val baseColor = DrappulaTheme.colors.onBackground.toColor()
            val selectedColor = baseColor
            val unselectedColor = baseColor.copy(alpha = 0.5f)
            NavigationBarItem(
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = title,
                    )
                },
                label = { Text(title) },
                colors =
                    NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedColor,
                        selectedTextColor = selectedColor,
                        unselectedIconColor = unselectedColor,
                        unselectedTextColor = unselectedColor,
                        indicatorColor = Color.Transparent,
                    ),
                modifier = Modifier.testTag(DashboardScreenTestTags.tabItem(tab)),
            )
        }
    }
}

object DashboardScreenTestTags {
    const val NAVIGATION_BAR = "DashboardScreenNavigationBar"
    private const val TAB_ITEM_PREFIX = "TabItem_"

    fun tabItem(tab: Tab) = "$TAB_ITEM_PREFIX${tab.name}"
}
