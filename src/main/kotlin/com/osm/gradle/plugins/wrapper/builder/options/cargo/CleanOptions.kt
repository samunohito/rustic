package com.osm.gradle.plugins.wrapper.builder.options.cargo

import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase

interface CleanOptions {
    class Doc : SingleCommandOptionBase(),
        CleanOptions {
        override val option: String = "--doc"
    }

    class Release : SingleCommandOptionBase(),
        CleanOptions {
        override val option: String = "--release"
    }

    class TargetDir : SingleCommandOptionBase(),
        CleanOptions {
        override val option: String = "--target-dir"
    }

    class Target : SingleCommandOptionBase(),
        CleanOptions {
        override val option: String = "--triple"
    }
}