package com.osm.gradle.plugins.types.config.options

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.ICleanOptions

class CleanOptions : RusticConfigurableBase(), ICleanOptions {
    override var color: String? = null
    override var doc: Boolean? = null
    override var frozen: Boolean? = null
    override var help: Boolean? = null
    override var locked: Boolean? = null
    override var manifestPath: String? = null
    override var offline: Boolean? = null
    override var packageName: Iterable<String?>? = null
    override var quiet: Boolean? = null
    override var release: Boolean? = null
    override var target: String? = null
    override var targetDir: String? = null
    override var verbose: Boolean? = null
}