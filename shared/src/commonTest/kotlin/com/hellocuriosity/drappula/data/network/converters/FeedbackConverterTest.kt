package com.hellocuriosity.drappula.data.network.converters

import com.hellocuriosity.drappula.data.network.models.ApiSlackMessage
import com.hellocuriosity.drappula.models.Feedback
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.time.Instant

class FeedbackConverterTest {
    private val converter = FeedbackConverter(channelId = "test-channel", platform = "test")

    @Test
    fun testFromFeedback() {
        val created = Instant.fromEpochMilliseconds(1000L)
        val feedback =
            Feedback(
                type = Feedback.Type.FEATURE,
                title = "Test Title",
                message = "Test Message",
                created = created,
            )

        val result = converter.from(feedback)

        assertEquals("test-channel", result.channel)
        assertNotNull(result.attachments)
        assertEquals(1, result.attachments?.size)

        val attachment = result.attachments!!.first()
        assertEquals("Test Title", attachment.fallback)
        assertEquals("#00C397", attachment.color)
        assertEquals("FEATURE", attachment.pretext)
        assertEquals("Test Title", attachment.title)
        assertEquals("Test Message", attachment.text)
        assertEquals(1000L, attachment.createdOn)
    }

    @Test
    fun testToThrowsNotImplemented() {
        val message = ApiSlackMessage()
        assertFailsWith<NotImplementedError> {
            converter.to(message)
        }
    }
}
