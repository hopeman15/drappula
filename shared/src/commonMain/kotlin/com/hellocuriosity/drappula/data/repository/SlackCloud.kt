package com.hellocuriosity.drappula.data.repository

import com.hellocuriosity.drappula.data.converters.Converter
import com.hellocuriosity.drappula.data.network.SlackService
import com.hellocuriosity.drappula.data.network.converters.FeedbackConverter
import com.hellocuriosity.drappula.data.network.models.ApiResponse
import com.hellocuriosity.drappula.data.network.models.ApiSlackMessage
import com.hellocuriosity.drappula.models.Feedback

open class SlackCloud(
    private val service: SlackService,
    private val feedbackConverter: Converter<Feedback, ApiSlackMessage>,
) {
    open suspend fun postFeedback(value: Feedback): Feedback? =
        service
            .post(feedbackConverter.from(value))
            .toValue(value)

    private fun <T : Any> ApiResponse.toValue(value: T): T? =
        this.let { response ->
            response.isSuccessful?.let { isSuccessful ->
                if (isSuccessful) value else null
            }
        }
}
