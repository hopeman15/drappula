package com.hellocuriosity.drappula.coroutines

import com.hellocuriosity.drappula.CoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.time.Duration.Companion.milliseconds

abstract class CoroutinesTestCase
    @OptIn(ExperimentalCoroutinesApi::class)
    constructor(
        dispatchers: CoroutineDispatcher = UnconfinedTestDispatcher(),
    ) {
        @get:Rule
        val coroutinesTestRule = CoroutinesTestRule(dispatchers)

        val dispatchers: CoroutineDispatchers
            get() = coroutinesTestRule.dispatchers

        @OptIn(ExperimentalCoroutinesApi::class)
        fun runBlockingTest(
            dispatchTimeoutMs: Long = 10_000L,
            block: suspend TestScope.() -> Unit,
        ) = coroutinesTestRule.scope.runTest(timeout = dispatchTimeoutMs.milliseconds, testBody = block)
    }
