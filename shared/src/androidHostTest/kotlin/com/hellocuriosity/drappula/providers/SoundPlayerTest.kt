package com.hellocuriosity.drappula.providers

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.MediaPlayer
import com.hellocuriosity.drappula.SoundPlayer
import com.hellocuriosity.drappula.models.Dracula
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import java.io.FileDescriptor
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class SoundPlayerTest {
    private val context: Context = mockk()
    private val mediaPlayer: MediaPlayer = mockk()

    private val assetManager: AssetManager = mockk()
    private val assetFileDescriptor: AssetFileDescriptor = mockk()
    private val fileDescriptor: FileDescriptor = mockk()
    private val soundPlayer: SoundPlayer =
        SoundPlayer(context, mediaPlayer)

    @BeforeTest
    fun setup() {
        every { context.assets } returns assetManager
        every { assetManager.openFd(any()) } returns assetFileDescriptor

        every { assetFileDescriptor.fileDescriptor } returns fileDescriptor
        every { assetFileDescriptor.startOffset } returns 0L
        every { assetFileDescriptor.length } returns 1000L
        every { assetFileDescriptor.close() } just Runs

        every { mediaPlayer.isPlaying } returns false
        every { mediaPlayer.stop() } just Runs
        every { mediaPlayer.reset() } just Runs
        every { mediaPlayer.setDataSource(any<FileDescriptor>(), any(), any()) } just Runs
        every { mediaPlayer.prepare() } just Runs
        every { mediaPlayer.start() } just Runs
        every { mediaPlayer.release() } just Runs
    }

    @AfterTest
    fun teardown() {
        confirmVerified(context, mediaPlayer, assetManager, assetFileDescriptor, fileDescriptor)
        clearAllMocks()
    }

    @Test
    fun testPlay() {
        val sound = Dracula.I
        soundPlayer.play(sound)

        verifyOrder {
            mediaPlayer.isPlaying
            mediaPlayer.reset()
            context.assets
            assetManager.openFd("audio/dracula/${sound.fileName}")
            assetFileDescriptor.fileDescriptor
            assetFileDescriptor.startOffset
            assetFileDescriptor.length
            mediaPlayer.setDataSource(fileDescriptor, 0L, 1000L)
            assetFileDescriptor.close()
            mediaPlayer.prepare()
            mediaPlayer.start()
        }
    }

    @Test
    fun testPlayStopsCurrentSoundFirst() {
        every { mediaPlayer.isPlaying } returns true

        val sound = Dracula.I
        soundPlayer.play(sound)

        verifyOrder {
            mediaPlayer.isPlaying
            mediaPlayer.stop()
            mediaPlayer.reset()
            context.assets
            assetManager.openFd("audio/dracula/${sound.fileName}")
            assetFileDescriptor.fileDescriptor
            assetFileDescriptor.startOffset
            assetFileDescriptor.length
            mediaPlayer.setDataSource(fileDescriptor, 0L, 1000L)
            assetFileDescriptor.close()
            mediaPlayer.prepare()
            mediaPlayer.start()
        }
    }

    @Test
    fun testStop() {
        every { mediaPlayer.isPlaying } returns true

        soundPlayer.stop()

        verify { mediaPlayer.isPlaying }
        verify { mediaPlayer.stop() }
        verify { mediaPlayer.reset() }
    }

    @Test
    fun testStopWhenNothingPlaying() {
        every { mediaPlayer.isPlaying } returns false

        soundPlayer.stop()

        verify { mediaPlayer.isPlaying }
        verify { mediaPlayer.reset() }
    }

    @Test
    fun testRelease() {
        every { mediaPlayer.isPlaying } returns false

        soundPlayer.release()

        verifyOrder {
            mediaPlayer.isPlaying
            mediaPlayer.reset()
            mediaPlayer.release()
        }
    }
}
