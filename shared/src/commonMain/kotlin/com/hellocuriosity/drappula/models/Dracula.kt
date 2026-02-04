package com.hellocuriosity.drappula.models

enum class Dracula(
    override val id: String,
    override val displayName: String,
    override val fileName: String,
    override val category: Category = Category.DRACULA,
) : Sound {
    I(id = "i", displayName = "i", fileName = "01_i.mp3"),
    AM(id = "am", displayName = "am", fileName = "02_am.mp3"),
    DRACULA(id = "dracula", displayName = "dracula", fileName = "03_dracula.mp3"),
    BID(id = "bid", displayName = "bid", fileName = "04_bid.mp3"),
    YOU(id = "you", displayName = "you", fileName = "05_you.mp3"),
    WELCOME(id = "welcome", displayName = "welcome", fileName = "06_welcome.mp3"),
    LISTEN(id = "listen", displayName = "listen", fileName = "07_listen.mp3"),
    TO(id = "to", displayName = "to", fileName = "08_to.mp3"),
    THEM(id = "them", displayName = "them", fileName = "09_them.mp3"),
    CHILDREN(id = "children", displayName = "children", fileName = "10_children.mp3"),
    OF(id = "of", displayName = "of", fileName = "11_of.mp3"),
    THE(id = "the", displayName = "the", fileName = "12_the.mp3"),
    NIGHT(id = "night", displayName = "night", fileName = "13_night.mp3"),
    WHAT(id = "what", displayName = "what", fileName = "14_what.mp3"),
    MUSIC(id = "music", displayName = "music", fileName = "15_music.mp3"),
    THEY(id = "they", displayName = "they", fileName = "16_they.mp3"),
    MAKE(id = "make", displayName = "make", fileName = "17_make.mp3"),
}
