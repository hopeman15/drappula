package com.hellocuriosity.drappula.ui.feedback

import app.cash.turbine.test
import com.hellocuriosity.drappula.coroutines.CoroutinesTestCase
import com.hellocuriosity.drappula.data.repository.SlackRepository
import com.hellocuriosity.drappula.models.Feedback
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.After
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultFeedbackViewModelTest : CoroutinesTestCase(StandardTestDispatcher()) {
    private val repository: SlackRepository = mockk()

    private val viewModel by lazy {
        DefaultFeedbackViewModel(
            repository = repository,
            dispatchers = dispatchers,
        )
    }

    @After
    fun teardown() {
        confirmVerified(repository)
    }

    @Test
    fun testInitialState() =
        runBlockingTest {
            viewModel.state.test {
                val initial = awaitItem()
                assertFalse(initial.loading)
                assertEquals(Feedback(), initial.feedback)
                assertNull(initial.error)
            }
        }

    @Test
    fun testUpdateFeedback() =
        runBlockingTest {
            val updated = Feedback(title = "Test", message = "A message")

            viewModel.state.test {
                awaitItem() // initial state

                viewModel.updateFeedback(updated)
                assertEquals(updated, awaitItem().feedback)
            }
        }

    @Test
    fun testSubmitSuccess() =
        runBlockingTest {
            coEvery { repository.uploadFeedback(any()) } returns null

            var navigated = false

            viewModel.state.test {
                awaitItem() // initial state

                viewModel.submit { navigated = true }
                testScheduler.advanceUntilIdle()

                assertTrue(navigated)
            }

            coVerify { repository.uploadFeedback(eq(Feedback())) }
        }

    @Test
    fun testSubmitFailureSetsError() =
        runBlockingTest {
            val exception = RuntimeException("network error")
            coEvery { repository.uploadFeedback(any()) } throws exception

            viewModel.state.test {
                awaitItem() // initial state

                viewModel.submit {}
                testScheduler.advanceUntilIdle()

                val errorState = awaitItem()
                assertFalse(errorState.loading)
                assertNotNull(errorState.error)
                assertEquals(exception, errorState.error)
            }

            coVerify { repository.uploadFeedback(eq(Feedback())) }
        }

    @Test
    fun testClearError() =
        runBlockingTest {
            val exception = RuntimeException("network error")
            coEvery { repository.uploadFeedback(any()) } throws exception

            viewModel.state.test {
                awaitItem() // initial state

                viewModel.submit {}
                testScheduler.advanceUntilIdle()

                val errorState = awaitItem()
                assertNotNull(errorState.error)

                viewModel.clearError()
                val clearedState = awaitItem()
                assertNull(clearedState.error)
            }

            coVerify { repository.uploadFeedback(eq(Feedback())) }
        }
}
