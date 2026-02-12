package com.hellocuriosity.drappula.data.converters

interface Converter<A, D> {
    fun from(value: A): D

    fun to(value: D): A
}
