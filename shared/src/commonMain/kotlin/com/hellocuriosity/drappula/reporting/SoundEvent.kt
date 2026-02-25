package com.hellocuriosity.drappula.reporting

import com.hellocuriosity.drappula.models.Sound

sealed class SoundEvent(
    override val tag: String,
) : AnalyticsEvent {
    data class Play(
        val sound: Sound,
    ) : SoundEvent(tag = TAG_PLAY_SOUND)

    override val extras: Map<String, String>
        get() =
            when (this) {
                is Play -> sound.toExtras()
            }

    private fun Sound.toExtras(): Map<String, String> =
        mapOf(
            AUDIO to "$displayName (${category.name})",
            CATEGORY to category.name,
        )

    companion object {
        const val TAG_PLAY_SOUND = "play_sound"
        const val AUDIO = "audio"
        const val CATEGORY = "category"
    }
}
