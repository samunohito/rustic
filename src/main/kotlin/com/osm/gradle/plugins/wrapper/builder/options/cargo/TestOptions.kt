package com.osm.gradle.plugins.wrapper.builder.options.cargo

import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase

interface TestOptions {
    class NoRun : SingleCommandOptionBase(),
        TestOptions {
        override val option: String = "--no-run"
    }

    class NoFailFast : SingleCommandOptionBase(),
        TestOptions {
        override val option: String = "--no-fail-fast"
    }
}