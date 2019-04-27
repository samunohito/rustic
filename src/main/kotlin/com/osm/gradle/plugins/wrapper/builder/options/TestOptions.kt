package com.osm.gradle.plugins.wrapper.builder.options

interface TestOptions {
    class NoRun : SingleBase(), TestOptions {
        override val option: String = "--no-run"
    }

    class NoFailFast : SingleBase(), TestOptions {
        override val option: String = "--no-fail-fast"
    }
}