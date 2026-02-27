package com.hellocuriosity.drappula.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.hellocuriosity.drappula.db.DrappulaDatabase
import com.hellocuriosity.drappula.models.Dracula
import com.hellocuriosity.drappula.models.Sound
import com.hellocuriosity.drappula.models.SoundSequence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SoundSequenceRepository(
    database: DrappulaDatabase,
) {
    private val sequenceQueries = database.soundSequenceQueries

    fun observeAll(): Flow<List<SoundSequence>> =
        sequenceQueries
            .selectAllSequences()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { entities ->
                entities.map { entity ->
                    val items =
                        sequenceQueries
                            .selectItemsBySequenceId(entity.id)
                            .executeAsList()
                    val sounds =
                        items.mapNotNull { item ->
                            Dracula.entries.find { it.id == item.sound_id }
                        }
                    SoundSequence(
                        id = entity.id,
                        name = entity.name,
                        sounds = sounds,
                    )
                }
            }

    fun create(
        name: String,
        sounds: List<Sound>,
    ) {
        sequenceQueries.transaction {
            sequenceQueries.insertSequence(
                name = name,
                created_at = currentTimeMillis(),
            )
            val sequenceId = sequenceQueries.lastInsertId().executeAsOne()
            sounds.forEachIndexed { index, sound ->
                sequenceQueries.insertItem(
                    sequence_id = sequenceId,
                    sound_id = sound.id,
                    position = index.toLong(),
                )
            }
        }
    }

    fun delete(id: Long) {
        sequenceQueries.transaction {
            sequenceQueries.deleteItemsBySequenceId(id)
            sequenceQueries.deleteSequence(id)
        }
    }
}
