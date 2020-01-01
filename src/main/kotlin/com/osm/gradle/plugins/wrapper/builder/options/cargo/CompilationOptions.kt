package com.osm.gradle.plugins.wrapper.builder.options.cargo

import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase

interface CompilationOptions {
    class Release : SingleCommandOptionBase(),
        CompilationOptions {
        override val option: String = "--release"
    }

    class Target(triple: String?) : SingleCommandOptionBase(triple),
        CompilationOptions {
        override val option: String = "--target"
    }

    class Profile : SingleCommandOptionBase(),
        CompilationOptions {
        override val option: String = "--profile"
    }
}