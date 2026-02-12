package com.hellocuriosity.drappula.models

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FeedbackTest {
    @Test
    fun testDefaultFeedback() {
        val feedback = Feedback()
        assertEquals(Feedback.Type.ENHANCEMENT, feedback.type)
        assertEquals(null, feedback.title)
        assertEquals(null, feedback.message)
        assertEquals(null, feedback.created)
    }

    @Test
    fun testIsCompleteWithValidData() {
        val feedback = Feedback(title = "Test Title", message = "Test Message")
        assertTrue(feedback.isComplete)
    }

    @Test
    fun testIsCompleteWithNullTitle() {
        val feedback = Feedback(title = null, message = "Test Message")
        assertFalse(feedback.isComplete)
    }

    @Test
    fun testIsCompleteWithNullMessage() {
        val feedback = Feedback(title = "Test Title", message = null)
        assertFalse(feedback.isComplete)
    }

    @Test
    fun testIsCompleteWithBlankTitle() {
        val feedback = Feedback(title = "  ", message = "Test Message")
        assertFalse(feedback.isComplete)
    }

    @Test
    fun testIsCompleteWithBlankMessage() {
        val feedback = Feedback(title = "Test Title", message = "  ")
        assertFalse(feedback.isComplete)
    }

    @Test
    fun testTypeColors() {
        assertEquals("#B562F2", Feedback.Type.ENHANCEMENT.color())
        assertEquals("#00C397", Feedback.Type.FEATURE.color())
        assertEquals("#F85F80", Feedback.Type.FIX.color())
        assertEquals("#FEE21D", Feedback.Type.OTHER.color())
    }

    @Test
    fun testTypeValues() {
        assertEquals("ENHANCEMENT", Feedback.Type.ENHANCEMENT.value)
        assertEquals("FEATURE", Feedback.Type.FEATURE.value)
        assertEquals("FIX", Feedback.Type.FIX.value)
        assertEquals("OTHER", Feedback.Type.OTHER.value)
    }

    @Test
    fun testTypeIndices() {
        assertEquals(0, Feedback.Type.INDEX_ENHANCEMENT)
        assertEquals(1, Feedback.Type.INDEX_FEATURE)
        assertEquals(2, Feedback.Type.INDEX_FIX)
        assertEquals(3, Feedback.Type.INDEX_OTHER)
    }
}
