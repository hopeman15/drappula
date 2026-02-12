package com.hellocuriosity.drappula

import com.hellocuriosity.drappula.data.repository.SlackRepository
import com.hellocuriosity.drappula.providers.PreferenceProvider
import io.mockk.every
import io.mockk.mockk

class MockApplication : DrappulaApplication() {
    private val dispatchers: CoroutineDispatchers =
        mockk {
            every { io } returns mockk()
            every { main } returns mockk()
        }

    private val preferenceProvider: PreferenceProvider =
        mockk(relaxed = true) {
            every { isClassicEnabled } returns false
        }

    private val slackRepository: SlackRepository = mockk(relaxed = true)

    private val applicationComponent: ApplicationComponent =
        mockk {
            every { applicationContext } returns this@MockApplication
            every { this@mockk.dispatchers } returns this@MockApplication.dispatchers
            every { this@mockk.preferenceProvider } returns this@MockApplication.preferenceProvider
            every { this@mockk.slackRepository } returns this@MockApplication.slackRepository
        }

    override fun getComponent(): ApplicationComponent = applicationComponent
}
