package com.osm.gradle.plugins.wrapper.builder.options.cargo

import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase

interface CommonOptions {
    class Help : SingleCommandOptionBase(),
        CommonOptions {
        override val option: String = "--help"
    }
}