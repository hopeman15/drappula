package com.hellocuriosity.drappula.provider

import com.hellocuriosity.drappula.SoundPlayer
import com.hellocuriosity.drappula.models.Dracula
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SoundPlayerTest {
    private val bundle: FakeResourceBundle = FakeResourceBundle()
    private val audioPlayer: FakeAudioPlayer = FakeAudioPlayer()
    private val soundPlayer: SoundPlayer =
        SoundPlayer(bundle, audioPlayer)

    @BeforeTest
    fun setup() {
        bundle.reset()
        audioPlayer.reset()
    }

    @Test
    fun testPlay() {
        val sound = Dracula.I
        soundPlayer.play(sound)

        assertTrue(bundle.urlForSoundCalled)
        assertEquals("dracula", bundle.lastRequestedCategory)
        assertEquals("01_i.mp3", bundle.lastRequestedFileName)
        assertTrue(audioPlayer.loadCalled)
        assertTrue(audioPlayer.prepareToPlayCalled)
        assertTrue(audioPlayer.playCalled)
    }

    @Test
    fun testPlayStopsCurrentSoundFirst() {
        val firstSound = Dracula.I
        soundPlayer.play(firstSound)

        audioPlayer.reset()

        val secondSound = Dracula.DRACULA
        soundPlayer.play(secondSound)

        assertTrue(audioPlayer.stopCalled)
        assertTrue(audioPlayer.loadCalled)
        assertTrue(audioPlayer.prepareToPlayCalled)
        assertTrue(audioPlayer.playCalled)
    }

    @Test
    fun testStop() {
        val sound = Dracula.I
        soundPlayer.play(sound)

        audioPlayer.reset()
        soundPlayer.stop()

        assertTrue(audioPlayer.stopCalled)
    }

    @Test
    fun testStopWhenNothingPlaying() {
        soundPlayer.stop()

        assertTrue(audioPlayer.stopCalled)
    }

    @Test
    fun testRelease() {
        soundPlayer.release()

        assertTrue(audioPlayer.releaseCalled)
    }
}
