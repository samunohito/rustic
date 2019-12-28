package com.osm.gradle.plugins.types.config.options

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.ITestOptions

class TestOptions : RusticConfigurableBase(), ITestOptions {
    override var noRun: Boolean? = null
    override var noFailFast: Boolean? = null
    override var doc: Boolean? = null
}