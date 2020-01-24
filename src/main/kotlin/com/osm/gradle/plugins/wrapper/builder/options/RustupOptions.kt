package com.osm.gradle.plugins.wrapper.builder.options

import com.osm.gradle.plugins.wrapper.builder.command.SingleCommandOptionBase

object RustupOptions {
    object Target {
        class Add(triple: String) : SingleCommandOptionBase(triple) {
            override val option: String = "add"
            override val hasValue: Boolean = true
        }

        class List : SingleCommandOptionBase() {
            override val option: String = "list"
            override val hasValue: Boolean = false
        }
    }
}