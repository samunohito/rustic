package com.osm.gradle.plugins.wrapper.builder.options

interface CleanOptions {
    class Doc : SingleBase(), CleanOptions {
        override val option: String = "--doc"
    }

    class Release : SingleBase(), CleanOptions {
        override val option: String = "--release"
    }

    class TargetDir : SingleBase(), CleanOptions {
        override val option: String = "--target-dir"
    }

    class Target : SingleBase(), CleanOptions {
        override val option: String = "--triple"
    }
}