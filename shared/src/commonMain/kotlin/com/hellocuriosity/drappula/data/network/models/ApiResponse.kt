package com.hellocuriosity.drappula.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @SerialName("ok")
    val isSuccessful: Boolean? = null,
    @SerialName("channel")
    val channel: String? = null,
    @SerialName("ts")
    val timestamp: String? = null,
)
