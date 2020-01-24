package com.osm.gradle.plugins.wrapper.builder.command

interface ICommandOption {
    val option: String
    val multiple: Boolean
    val hasValue: Boolean

    fun toList(): List<String>
}





























