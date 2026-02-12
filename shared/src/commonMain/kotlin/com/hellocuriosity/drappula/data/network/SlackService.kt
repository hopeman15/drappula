package com.hellocuriosity.drappula.data.network

import com.hellocuriosity.drappula.data.network.models.ApiResponse
import com.hellocuriosity.drappula.data.network.models.ApiSlackMessage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType

class SlackService(
    private val client: HttpClient,
    private val token: String,
) {
    suspend fun post(apiSlackMessage: ApiSlackMessage): ApiResponse =
        client
            .post(URL) {
                headers {
                    append(HttpHeaders.AcceptLanguage, LANGUAGE)
                    append(HttpHeaders.Authorization, "Bearer $token")
                    append(HttpHeaders.ContentType, APPLICATION_FORM)
                }
                contentType(ContentType.Application.Json)
                setBody(apiSlackMessage)
            }.body()

    companion object {
        private const val APPLICATION_FORM = "application/x-www-form-urlencoded"
        private const val LANGUAGE = "en-US"
        private const val URL: String = "https://slack.com/api/chat.postMessage"
    }
}
