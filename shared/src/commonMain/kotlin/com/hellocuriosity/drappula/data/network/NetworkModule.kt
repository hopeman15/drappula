package com.hellocuriosity.drappula.data.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkModule(
    factory: HttpEngineFactory,
    private val token: String,
) {
    val service: SlackService by lazy {
        SlackService(client = client, token = token)
    }

    private val client: HttpClient by lazy {
        HttpClient(factory.create()) {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true })
            }
        }
    }
}
