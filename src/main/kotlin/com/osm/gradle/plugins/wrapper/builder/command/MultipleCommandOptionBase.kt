package com.osm.gradle.plugins.wrapper.builder.command

/**
 * This class is used to represent command that are expected to be set multiple times.
 */
abstract class MultipleCommandOptionBase(param: Iterable<String?>?) : CommandOptionBase(param) {
    override val multiple: Boolean = true
}