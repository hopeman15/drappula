package com.hellocuriosity.drappula.models

import kotlin.test.Test

class DraculaTest : BaseSoundEnumTest<Dracula>() {
    override val entries = Dracula.entries

    override val values =
        listOf(
            ExpectedSound(id = "i", displayName = "i", fileName = "01_i.mp3"),
            ExpectedSound(id = "am", displayName = "am", fileName = "02_am.mp3"),
            ExpectedSound(id = "dracula", displayName = "dracula", fileName = "03_dracula.mp3"),
            ExpectedSound(id = "bid", displayName = "bid", fileName = "04_bid.mp3"),
            ExpectedSound(id = "you", displayName = "you", fileName = "05_you.mp3"),
            ExpectedSound(id = "welcome", displayName = "welcome", fileName = "06_welcome.mp3"),
            ExpectedSound(id = "listen", displayName = "listen", fileName = "07_listen.mp3"),
            ExpectedSound(id = "to", displayName = "to", fileName = "08_to.mp3"),
            ExpectedSound(id = "them", displayName = "them", fileName = "09_them.mp3"),
            ExpectedSound(id = "children", displayName = "children", fileName = "10_children.mp3"),
            ExpectedSound(id = "of", displayName = "of", fileName = "11_of.mp3"),
            ExpectedSound(id = "the", displayName = "the", fileName = "12_the.mp3"),
            ExpectedSound(id = "night", displayName = "night", fileName = "13_night.mp3"),
            ExpectedSound(id = "what", displayName = "what", fileName = "14_what.mp3"),
            ExpectedSound(id = "music", displayName = "music", fileName = "15_music.mp3"),
            ExpectedSound(id = "they", displayName = "they", fileName = "16_they.mp3"),
            ExpectedSound(id = "make", displayName = "make", fileName = "17_make.mp3"),
        )

    override val category = Category.DRACULA

    @Test
    fun runAllTests() = verifyAll()
}
