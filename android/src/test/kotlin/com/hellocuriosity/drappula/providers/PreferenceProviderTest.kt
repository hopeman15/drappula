package com.hellocuriosity.drappula.providers

import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PreferenceProviderTest {
    private lateinit var preferences: PreferenceProvider

    @Before
    fun setup() {
        preferences = PreferenceProvider(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testIsClassicEnabledDefault() {
        assertFalse(preferences.isClassicEnabled)
    }

    @Test
    fun testIsClassicEnabled() {
        preferences.isClassicEnabled = true
        assertTrue(preferences.isClassicEnabled)
    }
}
