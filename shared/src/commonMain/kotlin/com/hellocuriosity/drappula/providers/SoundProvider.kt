package com.hellocuriosity.drappula.providers

import com.hellocuriosity.drappula.models.Category
import com.hellocuriosity.drappula.models.Dracula
import com.hellocuriosity.drappula.models.Sound

class SoundProvider {
    fun soundFor(category: Category): List<Sound> =
        when (category) {
            Category.DRACULA -> Dracula.entries
        }
}
