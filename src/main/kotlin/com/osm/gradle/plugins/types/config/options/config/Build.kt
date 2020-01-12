package com.osm.gradle.plugins.types.config.options.config

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.config.IBuild

class Build : RusticConfigurableBase(), IBuild {
    override var jobs: Int? = null
    override var rustc: String? = null
    override var rustcWrapper: String? = null
    override var rustDoc: String? = null
    override var target: String? = null
    override var targetDir: String? = null
    override var rustFlags: Iterable<String?>? = null
    override var rustDocFlags: Iterable<String?>? = null
    override var incremental: Boolean? = null
    override var depInfoBaseDir: String? = null
}