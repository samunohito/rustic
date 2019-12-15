package com.osm.gradle.plugins.wrapper.builder.options

interface CompilationOptions {
    class Release : SingleBase(), CompilationOptions {
        override val option: String = "--release"
    }

    class Target(triple: String?) : SingleBase(triple), CompilationOptions {
        override val option: String = "--target"
    }

    class Profile : SingleBase(), CompilationOptions {
        override val option: String = "--profile"
    }
}