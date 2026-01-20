package com.hellocuriosity.drappula.ui

import androidx.lifecycle.ViewModel

/**
 * Extension function to call the protected onCleared() method on a ViewModel during testing.
 * Uses reflection to access the protected method without requiring production code changes.
 */
fun ViewModel.simulateCleared() {
    val method = ViewModel::class.java.getDeclaredMethod("onCleared")
    method.isAccessible = true
    method.invoke(this)
}
