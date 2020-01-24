package com.osm.gradle.plugins.wrapper.builder.command

/**
 * This class is used to represent command that cannot be set more than once.
 */
abstract class SingleCommandOptionBase(param: Iterable<String?>?) : CommandOptionBase(param) {
    override val multiple: Boolean = false

    constructor(param: String?) : this(listOf(param))
    constructor() : this(emptyList())
}