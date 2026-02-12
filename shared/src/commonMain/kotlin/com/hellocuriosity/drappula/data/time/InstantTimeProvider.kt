package com.hellocuriosity.drappula.data.time

import kotlin.time.Instant

fun interface InstantTimeProvider {
    fun now(): Instant
}
