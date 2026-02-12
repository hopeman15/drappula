package com.hellocuriosity.drappula.data.network

import io.ktor.client.engine.HttpClientEngine

expect class HttpEngineFactory() {
    fun create(): HttpClientEngine
}
