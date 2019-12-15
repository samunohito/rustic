package com.osm.gradle.plugins.util.string

fun String.beginWithLowerCase(): String {
    return when (this.length) {
        0 -> ""
        1 -> this.toLowerCase()
        else -> this[0].toLowerCase() + this.substring(1)
    }
}

fun String.beginWithUpperCase(): String {
    return when (this.length) {
        0 -> ""
        1 -> this.toUpperCase()
        else -> this[0].toUpperCase() + this.substring(1)
    }
}

fun String.toCamelCase(delimiter: Char = '_'): String {
    return this.split(delimiter).joinToString("") {
        it.beginWithUpperCase()
    }
}