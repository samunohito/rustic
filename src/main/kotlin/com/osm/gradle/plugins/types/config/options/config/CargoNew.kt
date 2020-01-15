package com.osm.gradle.plugins.types.config.options.config

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.config.ICargoNew

open class CargoNew : RusticConfigurableBase(), ICargoNew {
    override var name: String? = null
    override var email: String? = null
    override var vcs: String? = null

    open fun name(value: String?) {
        name = value
    }

    open fun email(value: String?) {
        email = value
    }

    open fun vcs(value: String?) {
        vcs = value
    }
}