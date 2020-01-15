package com.osm.gradle.plugins.types.config.options.config.target

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.config.target.ITriple

open class Triple : RusticConfigurableBase(), ITriple {
    override var linker: String? = null
    override var ar: String? = null
    override var runner: String? = null
    override var rustFlags: Iterable<String?>? = null

    open fun linker(value: String?) {
        linker = value
    }

    open fun ar(value: String?) {
        ar = value
    }

    open fun runner(value: String?) {
        runner = value
    }

    open fun rustFlags(value: Iterable<String?>?) {
        rustFlags = value
    }
}