package com.osm.gradle.plugins.types.config.options.option

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.config.options.config.CargoConfig
import com.osm.gradle.plugins.types.interfaces.options.option.IBase
import groovy.lang.Closure

abstract class Base : RusticConfigurableBase(), IBase {
    override var color: String? = null
    override var frozen: Boolean? = null
    override var locked: Boolean? = null
    override var manifestPath: String? = null
    override var offline: Boolean? = null
    override var packageName: Iterable<String?>? = null
    override var quiet: Boolean? = null
    override var target: String? = null
    override var targetDir: String? = null
    override var verbose: Boolean? = null
    override val cargoConfig: CargoConfig = CargoConfig()

    fun color(value: String?) {
        color = value
    }

    fun frozen(value: Boolean?) {
        frozen = value
    }

    fun locked(value: Boolean?) {
        locked = value
    }

    fun manifestPath(value: String?) {
        manifestPath = value
    }

    fun offline(value: Boolean?) {
        offline = value
    }

    fun packageName(value: Iterable<String?>?) {
        packageName = value
    }

    fun quiet(value: Boolean?) {
        quiet = value
    }

    fun target(value: String?) {
        target = value
    }

    fun targetDir(value: String?) {
        targetDir = value
    }

    fun verbose(value: Boolean?) {
        verbose = value
    }

    fun cargoConfig(closure: Closure<*>) {
        cargoConfig.configure(closure)
    }
}