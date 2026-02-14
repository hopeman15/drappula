package com.hellocuriosity.drappula.provider

import platform.Foundation.NSUserDefaults
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PreferenceProviderTest {
    private val preferences = PreferenceProvider()

    @AfterTest
    fun teardown() {
        NSUserDefaults.standardUserDefaults.removeObjectForKey("isClassicEnabled")
    }

    @Test
    fun testIsClassicEnabledDefault() {
        assertFalse(preferences.isClassicEnabled)
    }

    @Test
    fun testSetIsClassicEnabled() {
        preferences.isClassicEnabled = true
        assertTrue(preferences.isClassicEnabled)
    }

    @Test
    fun testSetIsClassicEnabledToFalse() {
        preferences.isClassicEnabled = true
        preferences.isClassicEnabled = false
        assertFalse(preferences.isClassicEnabled)
    }
}
