package com.hellocuriosity.drappula.reporting

interface CrashReporter {
    fun log(message: String)

    fun recordException(message: String)
}
