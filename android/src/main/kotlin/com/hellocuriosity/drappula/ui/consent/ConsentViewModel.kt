package com.hellocuriosity.drappula.ui.consent

import androidx.lifecycle.ViewModel
import com.hellocuriosity.drappula.ApplicationComponent
import com.hellocuriosity.drappula.consent.ConsentState
import com.hellocuriosity.drappula.consent.FirebaseConsentApplier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class ConsentViewModel : ViewModel() {
    abstract val consentState: StateFlow<ConsentState>
    abstract val hasResponded: StateFlow<Boolean>

    abstract fun updateConsent(state: ConsentState)

    abstract fun acceptAll()

    abstract fun declineAll()
}

class DefaultConsentViewModel(
    component: ApplicationComponent,
) : ConsentViewModel() {
    private val consentManager = component.consentManager

    private val _consentState = MutableStateFlow(consentManager.getConsentState())
    override val consentState: StateFlow<ConsentState> = _consentState.asStateFlow()

    private val _hasResponded = MutableStateFlow(consentManager.hasUserResponded())
    override val hasResponded: StateFlow<Boolean> = _hasResponded.asStateFlow()

    override fun updateConsent(state: ConsentState) {
        consentManager.updateConsent(state)
        FirebaseConsentApplier.apply(state)
        _consentState.value = state
        _hasResponded.value = true
    }

    override fun acceptAll() {
        updateConsent(ConsentState(analytics = true, crashReporting = true))
    }

    override fun declineAll() {
        updateConsent(ConsentState(analytics = false, crashReporting = false))
    }

    companion object {
        fun viewModel(component: ApplicationComponent): ConsentViewModel = DefaultConsentViewModel(component)
    }
}
