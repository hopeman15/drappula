package com.hellocuriosity.drappula.data.network.converters

import com.hellocuriosity.drappula.data.converters.Converter
import com.hellocuriosity.drappula.data.network.models.ApiAttachment
import com.hellocuriosity.drappula.data.network.models.ApiSlackMessage
import com.hellocuriosity.drappula.models.Feedback

class FeedbackConverter(
    private val channelId: String,
    private val platform: String,
) : Converter<Feedback, ApiSlackMessage> {
    override fun from(value: Feedback): ApiSlackMessage =
        ApiSlackMessage(
            channel = channelId,
            attachments =
                listOf(
                    ApiAttachment(
                        fallback = value.title,
                        color = value.type.color(),
                        pretext = value.type.value,
                        title = value.title,
                        text = value.message,
                        footer = "Drappula ($platform)",
                        createdOn = value.created?.toEpochMilliseconds(),
                    ),
                ),
        )

    override fun to(value: ApiSlackMessage): Feedback =
        throw NotImplementedError("ApiSlackMessage is not meant to be converted back to Feedback")
}
