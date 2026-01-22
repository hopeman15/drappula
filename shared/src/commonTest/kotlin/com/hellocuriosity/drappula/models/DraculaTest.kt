package com.hellocuriosity.drappula.models

import kotlin.test.Test

class DraculaTest : BaseSoundEnumTest<Dracula>() {
    override val entries = Dracula.entries

    override val values =
        listOf(
            ExpectedSound(id = "i_am", displayName = "I Am", fileName = "01_i_am.mp3"),
            ExpectedSound(id = "dracula", displayName = "Dracula", fileName = "02_dracula.mp3"),
        )

    override val category = Category.DRACULA

    @Test
    fun runAllTests() = verifyAll()
}
