package com.hellocuriosity.drappula

import android.content.Context
import com.hellocuriosity.drappula.providers.PreferenceProvider

class ApplicationComponent(
    val applicationContext: Context,
) {
    val dispatchers: CoroutineDispatchers by lazy { CoroutineDispatchers.default }
    val preferenceProvider: PreferenceProvider by lazy { PreferenceProvider(applicationContext) }
}
