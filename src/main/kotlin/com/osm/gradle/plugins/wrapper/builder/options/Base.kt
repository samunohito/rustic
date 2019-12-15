package com.osm.gradle.plugins.wrapper.builder.options

abstract class Base {
    abstract val option: String
    abstract val multiple: Boolean

    abstract fun toList(): List<String>
}





























