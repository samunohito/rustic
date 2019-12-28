package com.osm.gradle.plugins.types.config.options

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.ICleanOptions

class CleanOptions : RusticConfigurableBase(), ICleanOptions {
    override var doc: Boolean? = null
    override var release: Boolean? = null
    override var targetDir: String? = null
    override var triple: String? = null
}