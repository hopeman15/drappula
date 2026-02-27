package com.hellocuriosity.drappula.models

data class SoundSequence(
    val id: Long,
    val name: String,
    val sounds: List<Sound>,
)
