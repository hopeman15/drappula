package com.hellocuriosity.drappula.ui.feedback

import com.hellocuriosity.drappula.models.Feedback
import org.junit.Test
import kotlin.test.assertEquals

class FeedbackExtTest {
    @Test
    fun testEnhancementValueToTypeIdx() {
        assertEquals(
            Feedback.Type.INDEX_ENHANCEMENT,
            Feedback.Type.ENHANCEMENT.value
                .toTypeIdx(),
        )
    }

    @Test
    fun testFeatureValueToTypeIdx() {
        assertEquals(
            Feedback.Type.INDEX_FEATURE,
            Feedback.Type.FEATURE.value
                .toTypeIdx(),
        )
    }

    @Test
    fun testFixValueToTypeIdx() {
        assertEquals(
            Feedback.Type.INDEX_FIX,
            Feedback.Type.FIX.value
                .toTypeIdx(),
        )
    }

    @Test
    fun testOtherValueToTypeIdx() {
        assertEquals(
            Feedback.Type.INDEX_OTHER,
            Feedback.Type.OTHER.value
                .toTypeIdx(),
        )
    }

    @Test
    fun testUnknownValueToTypeIdxDefaultsToEnhancement() {
        assertEquals(Feedback.Type.INDEX_ENHANCEMENT, "UNKNOWN".toTypeIdx())
    }

    @Test
    fun testEnhancementIndexToType() {
        assertEquals(Feedback.Type.ENHANCEMENT, Feedback.Type.INDEX_ENHANCEMENT.toType())
    }

    @Test
    fun testFeatureIndexToType() {
        assertEquals(Feedback.Type.FEATURE, Feedback.Type.INDEX_FEATURE.toType())
    }

    @Test
    fun testFixIndexToType() {
        assertEquals(Feedback.Type.FIX, Feedback.Type.INDEX_FIX.toType())
    }

    @Test
    fun testOtherIndexToType() {
        assertEquals(Feedback.Type.OTHER, Feedback.Type.INDEX_OTHER.toType())
    }

    @Test
    fun testUnknownIndexToTypeDefaultsToEnhancement() {
        assertEquals(Feedback.Type.ENHANCEMENT, 99.toType())
    }
}
