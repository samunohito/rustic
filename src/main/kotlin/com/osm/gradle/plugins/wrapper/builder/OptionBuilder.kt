package com.osm.gradle.plugins.wrapper.builder

import com.osm.gradle.plugins.wrapper.builder.command.ICommandOption

class OptionBuilder {
    private val options = ArrayList<ICommandOption>()

    fun put(option: ICommandOption): OptionBuilder {
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