package com.osm.gradle.plugins.wrapper.builder.options

abstract class MultipleCommandOptionBase(param: Iterable<String?>?) : CommandOptionBase(param) {
    override val multiple: Boolean = true
}