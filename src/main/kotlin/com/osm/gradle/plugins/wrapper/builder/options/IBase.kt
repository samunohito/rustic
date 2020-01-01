package com.osm.gradle.plugins.wrapper.builder.options

interface IBase {
    val option: String
    val multiple: Boolean

    fun toList(): List<String>
}





























