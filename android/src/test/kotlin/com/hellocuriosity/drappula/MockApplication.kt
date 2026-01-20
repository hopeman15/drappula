package com.hellocuriosity.drappula

import io.mockk.every
import io.mockk.mockk

class MockApplication : DrappulaApplication() {
    private val dispatchers: CoroutineDispatchers =
        mockk {
            every { io } returns mockk()
            every { main } returns mockk()
        }

    private val applicationComponent: ApplicationComponent =
        mockk {
            every { applicationContext } returns this@MockApplication
            every { this@mockk.dispatchers } returns this@MockApplication.dispatchers
        }

    override fun getComponent(): ApplicationComponent = applicationComponent
}
