package com.osm.gradle.plugins.types.config.options.config

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.config.IRegistry

open class Registry : RusticConfigurableBase(), IRegistry {
    override var index: String? = null
    override var default: String? = null

    open fun index(value: String?) {
        index = value
    }

    open fun default(value: String?) {
        default = value
    }
}