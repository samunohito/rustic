package com.osm.gradle.plugins.types.interfaces.options.option

interface IBuild {
    val all: Boolean?
    val allFeatures: Boolean?
    val allTargets: Boolean?
    val bench: Iterable<String?>?
    val benches: Boolean?
    val bin: Iterable<String?>?
    val bins: Boolean?
    val example: Iterable<String?>?
    val examples: Boolean?
    val exclude: Iterable<String?>?
    val features: Iterable<String?>?
    val lib: Boolean?
    val messageFormat: String?
    val noDefaultFeatures: Boolean?
    val test: Iterable<String?>?
    val tests: Boolean?
    val workspace: Boolean?
}