package com.osm.gradle.plugins.types.interfaces.options

interface ISelection {
    val lib: Boolean?
    val bin: Iterable<String?>?
    val bins: Boolean?
    val example: Iterable<String?>?
    val examples: Boolean?
    val test: Iterable<String?>?
    val tests: Boolean?
    val bench: Iterable<String?>?
    val benches: Boolean?
}