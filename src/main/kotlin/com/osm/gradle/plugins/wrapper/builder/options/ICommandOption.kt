package com.osm.gradle.plugins.wrapper.builder.options

interface ICommandOption {
    val option: String
    val multiple: Boolean

    fun toList(): List<String>
}





























