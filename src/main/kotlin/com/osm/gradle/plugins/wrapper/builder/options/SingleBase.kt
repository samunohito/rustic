package com.osm.gradle.plugins.wrapper.builder.options

abstract class SingleBase(private val param: String?) : Base() {
    override val multiple: Boolean = false

    constructor() : this(null)

    override fun toString(): String {
        return option + if (param == null) "" else " $param"
    }
}