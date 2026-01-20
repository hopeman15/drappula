package com.hellocuriosity.drappula.coroutines

import com.hellocuriosity.drappula.CoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutinesTestRule(
    private val testDispatcher: CoroutineDispatcher,
) : TestWatcher() {
    val scope = TestScope(testDispatcher)
    val dispatchers: CoroutineDispatchers =
        object : CoroutineDispatchers {
            override val main: CoroutineDispatcher
                get() = testDispatcher
            override val default: CoroutineDispatcher
                get() = testDispatcher
            override val io: CoroutineDispatcher
                get() = testDispatcher
            override val unconfined: CoroutineDispatcher
                get() = testDispatcher
        }

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}
