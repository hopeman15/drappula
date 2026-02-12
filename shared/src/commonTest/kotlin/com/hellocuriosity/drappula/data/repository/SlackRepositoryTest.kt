package com.hellocuriosity.drappula.data.repository

import com.hellocuriosity.drappula.data.network.SlackService
import com.hellocuriosity.drappula.data.network.converters.FeedbackConverter
import com.hellocuriosity.drappula.data.time.InstantTimeProvider
import com.hellocuriosity.drappula.models.Feedback
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.time.Instant

class SlackRepositoryTest {
    private val fixedInstant = Instant.fromEpochMilliseconds(1234567890L)
    private val instantProvider = InstantTimeProvider { fixedInstant }

    @Test
    fun testUploadFeedbackStampsCreatedTime() =
        runTest {
            var capturedFeedback: Feedback? = null
            val fakeCloud =
                object : SlackCloud(
                    service =
                        SlackService(
                            client =
                                HttpClient(
                                    MockEngine { _ ->
                                        respond(
                                            content = """{"ok":true}""",
                                            headers =
                                                headersOf(
                                                    HttpHeaders.ContentType,
                                                    ContentType.Application.Json.toString(),
                                                ),
                                        )
                                    },
                                ) {
                                    install(ContentNegotiation) {
                                        json(
                                            json = Json { ignoreUnknownKeys = true },
                                        )
                                    }
                                },
                            token = "test-token",
                        ),
                    feedbackConverter = FeedbackConverter(channelId = "test-channel", platform = "test"),
                ) {
                    override suspend fun postFeedback(value: Feedback): Feedback? {
                        capturedFeedback = value
                        return value
                    }
                }

            val repository = SlackRepository(cloud = fakeCloud, instantProvider = instantProvider)
            val feedback = Feedback(title = "Test", message = "Message")

            val result = repository.uploadFeedback(feedback)

            assertNotNull(result)
            assertNotNull(capturedFeedback)
            assertEquals(fixedInstant, capturedFeedback?.created)
        }
}
