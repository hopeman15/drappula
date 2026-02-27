package com.hellocuriosity.drappula.ui.createsequence

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellocuriosity.drappula.ApplicationComponent
import com.hellocuriosity.drappula.CoroutineDispatchers
import com.hellocuriosity.drappula.data.SoundSequenceRepository
import com.hellocuriosity.drappula.models.Sound
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class CreateSequenceViewModel : ViewModel() {
    data class State(
        val selectedSounds: List<Sound> = emptyList(),
        val sequenceName: String = "",
        val isSaving: Boolean = false,
        val savedSuccessfully: Boolean = false,
        val error: Throwable? = null,
    )

    abstract val state: StateFlow<State>

    abstract fun addSound(sound: Sound)

    abstract fun removeSound(index: Int)

    abstract fun setName(name: String)

    abstract fun save()

    abstract fun reset()

    companion object {
        fun viewModel(component: ApplicationComponent): CreateSequenceViewModel =
            DefaultCreateSequenceViewModel(
                soundSequenceRepository = component.soundSequenceRepository,
                dispatchers = component.dispatchers,
            )
    }
}

class DefaultCreateSequenceViewModel(
    private val soundSequenceRepository: SoundSequenceRepository,
    private val dispatchers: CoroutineDispatchers,
) : CreateSequenceViewModel() {
    private val _state = MutableStateFlow(State())
    override val state: StateFlow<State> = _state.asStateFlow()

    override fun addSound(sound: Sound) {
        _state.update { it.copy(selectedSounds = it.selectedSounds + sound) }
    }

    override fun removeSound(index: Int) {
        _state.update {
            val mutable = it.selectedSounds.toMutableList()
            if (index in mutable.indices) {
                mutable.removeAt(index)
            }
            it.copy(selectedSounds = mutable)
        }
    }

    override fun setName(name: String) {
        _state.update { it.copy(sequenceName = name) }
    }

    @Suppress("TooGenericExceptionCaught")
    override fun save() {
        val currentState = _state.value
        if (currentState.sequenceName.isBlank() || currentState.selectedSounds.isEmpty()) return

        viewModelScope.launch(dispatchers.io) {
            _state.update { it.copy(isSaving = true, error = null) }
            try {
                soundSequenceRepository.create(
                    name = currentState.sequenceName.trim(),
                    sounds = currentState.selectedSounds,
                )
                _state.update { it.copy(isSaving = false, savedSuccessfully = true) }
            } catch (e: Exception) {
                _state.update { it.copy(isSaving = false, error = e) }
            }
        }
    }

    override fun reset() {
        _state.value = State()
    }
}
