package com.hellocuriosity.drappula

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
