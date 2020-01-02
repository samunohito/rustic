package com.osm.gradle.plugins.wrapper.builder.options

/**
 * This class is used to represent options that are expected to be set multiple times.
 */
abstract class MultipleCommandOptionBase(param: Iterable<String?>?) : CommandOptionBase(param) {
    override val multiple: Boolean = true
}