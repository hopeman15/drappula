package com.hellocuriosity.drappula.data.network.converters

import com.hellocuriosity.drappula.data.converters.Mapper
import com.hellocuriosity.drappula.data.network.models.ApiAttachment
import com.hellocuriosity.drappula.data.network.models.ApiSlackMessage
import com.hellocuriosity.drappula.models.Feedback

class FeedbackConverter(
    private val channelId: String,
    private val platform: String,
) : Mapper<Feedback, ApiSlackMessage> {
    override fun map(input: Feedback): ApiSlackMessage =
        ApiSlackMessage(
            channel = channelId,
            attachments =
                listOf(
                    ApiAttachment(
                        fallback = input.title,
                        color = input.type.color(),
                        pretext = input.type.value,
                        title = input.title,
                        text = input.message,
                        footer = "Drappula ($platform)",
                        createdOn = input.created?.toEpochMilliseconds(),
                    ),
                ),
        )
}
