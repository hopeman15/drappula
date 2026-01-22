package com.hellocuriosity.drappula.models

import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Abstract base class for Sound enum testing.
 *
 * Subclasses must define:
 * - [entries]: The list of enum entries to test
 * - [values]: List of [ExpectedSound] containing expected properties for each entry
 * - [category]: The expected category for all entries
 *
 * Subclasses should add a test method that calls [verifyAll] to enable IDE test discovery:
 * ```
 * @Test
 * fun runAllTests() = verifyAll()
 * ```
 */
abstract class BaseSoundEnumTest<T> where T : Enum<T>, T : Sound {
    data class ExpectedSound(
        val id: String,
        val displayName: String,
        val fileName: String,
    )

    abstract val entries: List<T>
    abstract val values: List<ExpectedSound>
    abstract val category: Category

    /**
     * Runs all verification checks. Call this from a @Test method in subclasses.
     */
    fun verifyAll() {
        verifyEntryCount()
        verifyIds()
        verifyDisplayNames()
        verifyFileNames()
        verifyCategory()
        verifySoundInterface()
        verifyUniqueIds()
        verifyUniqueFileNames()
        verifyValidExtensions()
    }

    private fun verifyEntryCount() {
        assertEquals(
            expected = values.size,
            actual = entries.size,
            message = "Enum entry count mismatch",
        )
    }

    private fun verifyIds() {
        entries.zip(values).forEach { (entry, expected) ->
            assertEquals(
                expected = expected.id,
                actual = entry.id,
                message = "Entry ${entry.name} has incorrect id",
            )
        }
    }

    private fun verifyDisplayNames() {
        entries.zip(values).forEach { (entry, expected) ->
            assertEquals(
                expected = expected.displayName,
                actual = entry.displayName,
                message = "Entry ${entry.name} has incorrect displayName",
            )
        }
    }

    private fun verifyFileNames() {
        entries.zip(values).forEach { (entry, expected) ->
            assertEquals(
                expected = expected.fileName,
                actual = entry.fileName,
                message = "Entry ${entry.name} has incorrect fileName",
            )
        }
    }

    private fun verifyCategory() {
        entries.forEach { entry ->
            assertEquals(
                expected = category,
                actual = entry.category,
                message = "Entry ${entry.name} has incorrect category",
            )
        }
    }

    private fun verifySoundInterface() {
        entries.forEach { entry ->
            val sound: Sound = entry
            assertEquals(entry.id, sound.id)
            assertEquals(entry.displayName, sound.displayName)
            assertEquals(entry.fileName, sound.fileName)
            assertEquals(entry.category, sound.category)
        }
    }

    private fun verifyUniqueIds() {
        val ids = entries.map { it.id }
        assertEquals(
            expected = ids.size,
            actual = ids.distinct().size,
            message = "Duplicate ids found",
        )
    }

    private fun verifyUniqueFileNames() {
        val fileNames = entries.map { it.fileName }
        assertEquals(
            expected = fileNames.size,
            actual = fileNames.distinct().size,
            message = "Duplicate fileNames found",
        )
    }

    private fun verifyValidExtensions() {
        val validExtensions = listOf("mp3")
        entries.forEach { entry ->
            val extension = entry.fileName.substringAfterLast(".", "")
            assertTrue(
                actual = extension in validExtensions,
                message = "Entry ${entry.name} has invalid file extension: $extension",
            )
        }
    }
}
