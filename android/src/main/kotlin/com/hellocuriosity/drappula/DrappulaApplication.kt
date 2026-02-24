package com.hellocuriosity.drappula

import android.app.Application
import com.hellocuriosity.drappula.consent.FirebaseConsentApplier

open class DrappulaApplication :
    Application(),
    ApplicationComponentProvider {
    private var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        val consentManager = getComponent().consentManager
        if (consentManager.hasUserResponded()) {
            FirebaseConsentApplier.apply(consentManager.getConsentState())
        }
    }

    override fun getComponent(): ApplicationComponent {
        if (applicationComponent == null) {
            synchronized(this) {
                if (applicationComponent == null) {
                    applicationComponent = ApplicationComponent(this)
                }
            }
        }
        return applicationComponent!!
    }
}
