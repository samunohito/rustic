package com.osm.gradle.plugins.wrapper.builder

import com.osm.gradle.plugins.wrapper.builder.options.IBase

class OptionBuilder {
    private val options = ArrayList<IBase>()

    fun put(option: IBase): OptionBuilder {
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
            .map { it.toList() }
            .filter { it.isNotEmpty() }
            .flatten()
            .toList()
    }

    override fun toString(): String {
        return toList().joinToString(" ")
    }
}