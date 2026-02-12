package com.hellocuriosity.drappula.ui.feedback

import com.hellocuriosity.drappula.models.Feedback

fun String.toTypeIdx(): Int =
    when (this) {
        Feedback.Type.ENHANCEMENT.value -> Feedback.Type.INDEX_ENHANCEMENT
        Feedback.Type.FEATURE.value -> Feedback.Type.INDEX_FEATURE
        Feedback.Type.FIX.value -> Feedback.Type.INDEX_FIX
        Feedback.Type.OTHER.value -> Feedback.Type.INDEX_OTHER
        else -> Feedback.Type.INDEX_ENHANCEMENT
    }

fun Int.toType(): Feedback.Type =
    when (this) {
        Feedback.Type.INDEX_ENHANCEMENT -> Feedback.Type.ENHANCEMENT
        Feedback.Type.INDEX_FEATURE -> Feedback.Type.FEATURE
        Feedback.Type.INDEX_FIX -> Feedback.Type.FIX
        Feedback.Type.INDEX_OTHER -> Feedback.Type.OTHER
        else -> Feedback.Type.ENHANCEMENT
    }
