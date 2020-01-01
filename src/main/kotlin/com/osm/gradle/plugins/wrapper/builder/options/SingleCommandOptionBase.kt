package com.osm.gradle.plugins.wrapper.builder.options

abstract class SingleCommandOptionBase(param: Iterable<String?>?) : CommandOptionBase(param) {
    override val multiple: Boolean = false

    constructor(param: String?) : this(listOf(param))
    constructor() : this(emptyList())
}