package com.hellocuriosity.drappula.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.hellocuriosity.drappula.R

enum class Tab(
    @StringRes val titleRes: Int,
    val icon: ImageVector,
) {
    AUDIO(titleRes = R.string.tab_audio, icon = Icons.AutoMirrored.Filled.VolumeUp),
    SETTINGS(titleRes = R.string.tab_settings, icon = Icons.Default.Settings),
}
