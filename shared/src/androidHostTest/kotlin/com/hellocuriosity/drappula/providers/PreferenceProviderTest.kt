package com.hellocuriosity.drappula.providers

import android.content.Context
import android.content.SharedPreferences
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PreferenceProviderTest {
    private val editor: SharedPreferences.Editor = mockk()
    private val prefs: SharedPreferences = mockk()
    private val context: Context = mockk()

    private lateinit var preferences: PreferenceProvider

    @BeforeTest
    fun setup() {
        every { context.getSharedPreferences("DRAPPULA_PREFERENCES", Context.MODE_PRIVATE) } returns prefs
        every { prefs.edit() } returns editor
        every { editor.putBoolean(any(), any()) } returns editor
        every { editor.apply() } just Runs
        preferences = PreferenceProvider(context)
    }

    @AfterTest
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun testIsClassicEnabledDefault() {
        every { prefs.getBoolean("IS_CLASSIC_ENABLED", false) } returns false
        assertFalse(preferences.isClassicEnabled)
    }

    @Test
    fun testIsClassicEnabled() {
        every { prefs.getBoolean("IS_CLASSIC_ENABLED", false) } returns true
        assertTrue(preferences.isClassicEnabled)
    }

    @Test
    fun testSetIsClassicEnabled() {
        preferences.isClassicEnabled = true
        verify { editor.putBoolean("IS_CLASSIC_ENABLED", true) }
    }
}
