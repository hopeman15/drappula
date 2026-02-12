package com.hellocuriosity.drappula.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiSlackMessage(
    @SerialName("channel")
    val channel: String? = null,
    @SerialName("attachments")
    val attachments: List<ApiAttachment>? = null,
)
