package com.hellocuriosity.drappula.data.converters

fun interface Mapper<I, O> {
    fun map(input: I): O
}
