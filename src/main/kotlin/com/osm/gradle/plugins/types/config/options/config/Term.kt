package com.osm.gradle.plugins.types.config.options.config

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.config.ITerm

open class Term : RusticConfigurableBase(), ITerm {
    override var verbose: Boolean? = null
    override var color: String? = null

    open fun verbose(value: Boolean?) {
        verbose = value
    }

    open fun color(value: String?) {
        color = value
    }
}