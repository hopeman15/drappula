package com.hellocuriosity.drappula.ui.soundplayer

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellocuriosity.drappula.ApplicationComponent
import com.hellocuriosity.drappula.CoroutineDispatchers
import com.hellocuriosity.drappula.SoundPlayer
import com.hellocuriosity.drappula.SoundSequencer
import com.hellocuriosity.drappula.data.SoundSequenceRepository
import com.hellocuriosity.drappula.models.Sound
import com.hellocuriosity.drappula.models.SoundSequence
import com.hellocuriosity.drappula.reporting.ReportHandler
import com.hellocuriosity.drappula.reporting.SoundEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class SoundPlayerViewModel : ViewModel() {
    data class State(
        val isPlaying: Boolean = false,
        val error: Throwable? = null,
        val sequences: List<SoundSequence> = emptyList(),
    )

    abstract val state: StateFlow<State>

    abstract fun playSound(sound: Sound)

    abstract fun playSequence(sequence: SoundSequence)

    abstract fun deleteSequence(id: Long)

    companion object {
        fun viewModel(component: ApplicationComponent): SoundPlayerViewModel {
            val mediaPlayer = MediaPlayer()
            val soundPlayer = SoundPlayer(component.applicationContext, mediaPlayer)
            val soundSequencer = SoundSequencer(soundPlayer)
            return DefaultSoundPlayerViewModel(
                soundPlayer = soundPlayer,
                soundSequencer = soundSequencer,
                soundSequenceRepository = component.soundSequenceRepository,
                dispatchers = component.dispatchers,
                reportHandler = component.reportHandler,
            )
        }
    }
}

class DefaultSoundPlayerViewModel(
    private val soundPlayer: SoundPlayer,
    private val soundSequencer: SoundSequencer,
    private val soundSequenceRepository: SoundSequenceRepository,
    private val dispatchers: CoroutineDispatchers,
    private val reportHandler: ReportHandler,
) : SoundPlayerViewModel() {
    private val _state = MutableStateFlow(State())
    override val state: StateFlow<State> = _state.asStateFlow()

    init {
        viewModelScope.launch(dispatchers.io) {
            soundSequenceRepository.observeAll().collect { sequences ->
                _state.update { it.copy(sequences = sequences) }
            }
        }
    }

    @Suppress("TooGenericExceptionCaught")
    override fun playSound(sound: Sound) {
        viewModelScope.launch(dispatchers.io) {
            try {
                _state.update { it.copy(isPlaying = true, error = null) }
                soundPlayer.play(sound)
                _state.update { it.copy(isPlaying = false) }
                reportHandler.logEvent(SoundEvent.Play(sound))
            } catch (e: Exception) {
                _state.update { it.copy(isPlaying = false, error = e) }
                reportHandler.reportException(e)
            }
        }
    }

    @Suppress("TooGenericExceptionCaught")
    override fun playSequence(sequence: SoundSequence) {
        viewModelScope.launch(dispatchers.io) {
            try {
                _state.update { it.copy(isPlaying = true, error = null) }
                soundSequencer.play(sequence.sounds) {
                    _state.update { it.copy(isPlaying = false) }
                }
            } catch (e: Exception) {
                _state.update { it.copy(isPlaying = false, error = e) }
                reportHandler.reportException(e)
            }
        }
    }

    override fun deleteSequence(id: Long) {
        viewModelScope.launch(dispatchers.io) {
            soundSequenceRepository.delete(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        soundSequencer.stop()
        soundPlayer.release()
    }
}
