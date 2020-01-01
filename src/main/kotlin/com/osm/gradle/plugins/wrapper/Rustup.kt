package com.osm.gradle.plugins.wrapper

import com.osm.gradle.plugins.wrapper.builder.OptionBuilder

class Rustup : RustToolBase() {
    override val executable: String = "rustup"

    fun target(options: OptionBuilder = OptionBuilder()): String {
        return run("target", options)
    }
}