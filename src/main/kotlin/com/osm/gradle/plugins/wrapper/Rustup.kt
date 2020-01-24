package com.osm.gradle.plugins.wrapper

import com.osm.gradle.plugins.wrapper.builder.OptionBuilder

/**
 * Wraps the functionality of the options command.
 */
class Rustup : RustToolBase() {
    override val executable: String = "options"

    fun target(options: OptionBuilder = OptionBuilder()): String {
        return run("target", options)
    }
}