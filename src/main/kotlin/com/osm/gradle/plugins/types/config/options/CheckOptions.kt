package com.osm.gradle.plugins.types.config.options

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.ICheckOptions

class CheckOptions : RusticConfigurableBase(), ICheckOptions {
    override var profile: String? = null
}