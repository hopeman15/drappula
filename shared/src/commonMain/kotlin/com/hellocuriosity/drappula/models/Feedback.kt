package com.hellocuriosity.drappula.models

import kotlin.time.Instant

data class Feedback(
    val type: Type = Type.ENHANCEMENT,
    val title: String? = null,
    val message: String? = null,
    val created: Instant? = null,
) {
    enum class Type(
        val value: String,
    ) {
        ENHANCEMENT("ENHANCEMENT"),
        FEATURE("FEATURE"),
        FIX("FIX"),
        OTHER("OTHER"),
        ;

        companion object {
            const val INDEX_ENHANCEMENT: Int = 0
            const val INDEX_FEATURE: Int = 1
            const val INDEX_FIX: Int = 2
            const val INDEX_OTHER: Int = 3
        }

        fun color(): String =
            when (this) {
                ENHANCEMENT -> "#B562F2"
                FEATURE -> "#00C397"
                FIX -> "#F85F80"
                OTHER -> "#FEE21D"
            }
    }

    val isComplete: Boolean
        get() = !title.isNullOrBlank() && !message.isNullOrBlank()
}
