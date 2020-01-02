package com.osm.gradle.plugins.wrapper.builder.options

/**
 * This class represents one option and its parameters.
 */
abstract class CommandOptionBase(private val param: Iterable<String?>?) : ICommandOption {
    /**
     * Turns an option and its parameters into a list.
     */
    override fun toList(): List<String> {
        val p = param?.filterNotNull()
        return if (p != null) {
            listOfNotNull(option).plus(p)
        } else {
            listOfNotNull(option)
        }
    }
}