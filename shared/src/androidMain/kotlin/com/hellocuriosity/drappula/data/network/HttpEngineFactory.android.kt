package com.hellocuriosity.drappula.data.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual class HttpEngineFactory actual constructor() {
    actual fun create(): HttpClientEngine = OkHttp.create()
}
