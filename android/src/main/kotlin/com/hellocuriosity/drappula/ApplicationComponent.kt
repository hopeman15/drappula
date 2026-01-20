package com.hellocuriosity.drappula

import android.content.Context

class ApplicationComponent(
    val applicationContext: Context,
) {
    val dispatchers: CoroutineDispatchers by lazy { CoroutineDispatchers.default }
}
