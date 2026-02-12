package com.hellocuriosity.drappula.data.repository

import com.hellocuriosity.drappula.data.network.SlackService
import com.hellocuriosity.drappula.data.network.converters.FeedbackConverter
import com.hellocuriosity.drappula.data.network.models.ApiResponse
import com.hellocuriosity.drappula.models.Feedback
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class SlackCloudTest {
    private fun createMockClient(isSuccessful: Boolean): HttpClient {
        val responseJson =
            Json.encodeToString(
                ApiResponse.serializer(),
                ApiResponse(isSuccessful = isSuccessful),
            )
        return HttpClient(
            MockEngine { _ ->
                respond(
                    content = responseJson,
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
                )
            },
        ) {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true })
            }
        }
    }

    @Test
    fun testPostFeedbackSuccess() =
        runTest {
            val client = createMockClient(isSuccessful = true)
            val service = SlackService(client, token = "test-token")
            val cloud = SlackCloud(service = service, feedbackConverter = FeedbackConverter(channelId = "test-channel", platform = "test"))

            val feedback = Feedback(title = "Test", message = "Test Message")
            val result = cloud.postFeedback(feedback)

            assertNotNull(result)
            assertEquals("Test", result.title)
        }

    @Test
    fun testPostFeedbackFailure() =
        runTest {
            val client = createMockClient(isSuccessful = false)
            val service = SlackService(client, token = "test-token")
            val cloud = SlackCloud(service = service, feedbackConverter = FeedbackConverter(channelId = "test-channel", platform = "test"))

            val feedback = Feedback(title = "Test", message = "Test Message")
            val result = cloud.postFeedback(feedback)

            assertNull(result)
        }
}
