package com.osm.gradle.plugins.util.iterable

object IterableExtensions {
    fun <T> Iterable<T>.concat(tail: Iterable<T>): Iterable<T> {
        val ret = this.toMutableList()
        ret.addAll(tail)
        return ret
    }
}