package com.hellocuriosity.drappula.data.repository

import com.hellocuriosity.drappula.data.time.InstantTimeProvider
import com.hellocuriosity.drappula.models.Feedback
import kotlin.time.Clock

class SlackRepository(
    private val cloud: SlackCloud,
    private val instantProvider: InstantTimeProvider = InstantTimeProvider { Clock.System.now() },
) {
    suspend fun uploadFeedback(value: Feedback): Feedback? =
        cloud.postFeedback(
            value = value.copy(created = instantProvider.now()),
        )
}
