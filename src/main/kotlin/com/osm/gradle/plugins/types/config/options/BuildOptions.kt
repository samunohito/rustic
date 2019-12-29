package com.osm.gradle.plugins.types.config.options

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.IBuildOptions

class BuildOptions : RusticConfigurableBase(), IBuildOptions {
    override var outputDirectory: String? = null
    override var debug: Boolean? = null

    fun outputDirectory(value: String?) {
        outputDirectory = value
    }

    fun debug(value: Boolean?) {
        debug = value
    }
}