package com.osm.gradle.plugins.wrapper.builder

import com.osm.gradle.plugins.wrapper.builder.options.Base

class OptionBuilder {
    private val options = ArrayList<Base>()

    fun put(option: Base): OptionBuilder {
        if (!option.multiple) {
            removeIfMatchType(option.javaClass)
        }

        options.add(option)
        return this
    }

    fun removeIfMatchType(option: Class<*>): OptionBuilder {
        options.removeIf { option == it.javaClass }
        return this
    }

    fun toList(): List<String> {
        return options
            .map { it.toString() }
            .filter { it.isNotEmpty() }
            .toList()
    }

    override fun toString(): String {
        return toList().joinToString(" ")
    }
}