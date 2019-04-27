package com.osm.gradle.plugins.wrapper.builder.options

interface CommonOptions {
    class Help : SingleBase(), CommonOptions {
        override val option: String = "--help"
    }
}