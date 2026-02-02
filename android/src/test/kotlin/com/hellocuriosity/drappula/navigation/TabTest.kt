package com.hellocuriosity.drappula.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Settings
import com.hellocuriosity.drappula.R
import org.junit.Test
import kotlin.test.assertEquals

class TabTest {
    @Test
    fun testTabEntriesCount() {
        assertEquals(2, Tab.entries.size)
    }

    @Test
    fun testAudioTabProperties() {
        val tab = Tab.AUDIO
        assertEquals(R.string.tab_audio, tab.titleRes)
        assertEquals(Icons.AutoMirrored.Filled.VolumeUp, tab.icon)
    }

    @Test
    fun testSettingsTabProperties() {
        val tab = Tab.SETTINGS
        assertEquals(R.string.tab_settings, tab.titleRes)
        assertEquals(Icons.Default.Settings, tab.icon)
    }

    @Test
    fun testTabEntriesOrder() {
        val entries = Tab.entries
        assertEquals(Tab.AUDIO, entries[0])
        assertEquals(Tab.SETTINGS, entries[1])
    }
}
