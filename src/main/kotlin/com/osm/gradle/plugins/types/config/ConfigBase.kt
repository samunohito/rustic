package com.osm.gradle.plugins.types.config

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.config.options.*
import com.osm.gradle.plugins.types.config.options.config.CargoConfig
import com.osm.gradle.plugins.types.interfaces.IConfigBase
import groovy.lang.Closure
import org.gradle.api.NamedDomainObjectFactory
import org.gradle.api.plugins.ExtensionContainer

abstract class ConfigBase(override val name: String) : RusticConfigurableBase(), IConfigBase {
    override var enabled: Boolean? = true
    override var targetDir: String? = null
    override var environments: Map<String?, String?>? = HashMap()
    override var target: String? = null
    override var features: Iterable<String?>? = null

    override val buildOptions: BuildOptions = BuildOptions()
    override val checkOptions: CheckOptions = CheckOptions()
    override val testOptions: TestOptions = TestOptions()
    override val benchOptions: BenchOptions = BenchOptions()
    override val cleanOptions: CleanOptions = CleanOptions()
    override val cargoConfig: CargoConfig = CargoConfig()

    open fun enabled(value: Boolean?) {
        enabled = value
    }

    open fun targetDir(value: String?) {
        targetDir = value
    }

    open fun environments(map: Map<String?, String?>?) {
        environments = map
    }

    open fun target(value: String?) {
        this.target = value
    }

    open fun features(value: Iterable<String?>?) {
        this.features = value
    }

    open fun buildOptions(closure: Closure<*>) {
        buildOptions.configure(closure)
    }

    open fun checkOptions(closure: Closure<*>) {
        checkOptions.configure(closure)
    }

    open fun testOptions(closure: Closure<*>) {
        testOptions.configure(closure)
    }

    open fun benchOptions(closure: Closure<*>) {
        benchOptions.configure(closure)
    }

    open fun cleanOptions(closure: Closure<*>) {
        cleanOptions.configure(closure)
    }

    open fun cargoConfig(closure: Closure<*>) {
        cargoConfig.configure(closure)
    }

    class Factory<T : ConfigBase>(private val extensions: ExtensionContainer, private val type: Class<T>) :
        NamedDomainObjectFactory<T> {
        override fun create(name: String): T {
            return extensions.create(name, type, name)
        }
    }
}