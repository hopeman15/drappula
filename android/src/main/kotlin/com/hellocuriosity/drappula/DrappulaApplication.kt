package com.hellocuriosity.drappula

import android.app.Application

open class DrappulaApplication :
    Application(),
    ApplicationComponentProvider {
    private var applicationComponent: ApplicationComponent? = null

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
