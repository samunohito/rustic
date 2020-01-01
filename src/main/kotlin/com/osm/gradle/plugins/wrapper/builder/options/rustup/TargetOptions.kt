package com.osm.gradle.plugins.wrapper.builder.options.rustup

import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase

interface TargetOptions {
    class Add(triple: String) : SingleCommandOptionBase(triple), TargetOptions {
        override val option: String = "add"
    }

    class List : SingleCommandOptionBase(), TargetOptions {
        override val option: String = "list"
    }
}