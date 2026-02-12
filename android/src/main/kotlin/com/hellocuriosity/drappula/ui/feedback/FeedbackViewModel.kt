package com.hellocuriosity.drappula.ui.feedback

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellocuriosity.drappula.ApplicationComponent
import com.hellocuriosity.drappula.CoroutineDispatchers
import com.hellocuriosity.drappula.data.repository.SlackRepository
import com.hellocuriosity.drappula.models.Feedback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class FeedbackViewModel : ViewModel() {
    data class State(
        val loading: Boolean = false,
        val feedback: Feedback = Feedback(),
        val error: Throwable? = null,
    )

    abstract val state: StateFlow<State>

    abstract fun updateFeedback(value: Feedback)

    abstract fun submit(navigate: () -> Unit)

    abstract fun clearError()

    companion object {
        fun viewModel(component: ApplicationComponent): FeedbackViewModel =
            DefaultFeedbackViewModel(
                repository = component.slackRepository,
                dispatchers = component.dispatchers,
            )
    }
}

class DefaultFeedbackViewModel(
    private val repository: SlackRepository,
    private val dispatchers: CoroutineDispatchers,
) : FeedbackViewModel() {
    private val loadingState = MutableStateFlow(false)
    private val feedback = MutableStateFlow(Feedback())
    private val errorState = MutableStateFlow<Throwable?>(null)

    override val state: StateFlow<State> =
        combine(
            loadingState,
            feedback,
            errorState,
        ) { isLoading, currentFeedback, error ->
            State(loading = isLoading, feedback = currentFeedback, error = error)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), State())

    override fun updateFeedback(value: Feedback) {
        feedback.value = value
    }

    override fun submit(navigate: () -> Unit) {
        viewModelScope.launch(dispatchers.io) {
            loadingState.value = true
            runCatching {
                repository.uploadFeedback(feedback.value)
            }.onSuccess {
                loadingState.value = false
                launch(dispatchers.main) { navigate() }
            }.onFailure { throwable ->
                loadingState.value = false
                errorState.value = throwable
            }
        }
    }

    override fun clearError() {
        errorState.value = null
    }
}
