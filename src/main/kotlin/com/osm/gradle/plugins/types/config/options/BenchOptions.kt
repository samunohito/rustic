package com.osm.gradle.plugins.types.config.options

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.IBenchOptions

class BenchOptions : RusticConfigurableBase(), IBenchOptions {
    override var noRun: Boolean? = null
    override var noFailFast: Boolean? = null
}