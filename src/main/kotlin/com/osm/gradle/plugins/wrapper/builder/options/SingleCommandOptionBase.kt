package com.osm.gradle.plugins.wrapper.builder.options

/**
 * This class is used to represent options that cannot be set more than once.
 */
abstract class SingleCommandOptionBase(param: Iterable<String?>?) : CommandOptionBase(param) {
    override val multiple: Boolean = false

    constructor(param: String?) : this(listOf(param))
    constructor() : this(emptyList())
}