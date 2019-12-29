package com.osm.gradle.plugins.types.config

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.config.options.*
import com.osm.gradle.plugins.types.interfaces.IConfigBase
import groovy.lang.Closure
import groovy.lang.MissingPropertyException
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.internal.extensibility.DefaultExtraPropertiesExtension

abstract class ConfigBase(override val name: String) : RusticConfigurableBase(), IConfigBase {
    override var enabled: Boolean? = true
    override var targetDir: String? = null
    override var environments: Map<String, String> = HashMap()
    override var target: String? = null
    override var features: Iterable<String>? = null

    override val targetSelection: Selection = Selection()
    override val buildOptions: BuildOptions = BuildOptions()
    override val checkOptions: CheckOptions = CheckOptions()
    override val testOptions: TestOptions = TestOptions()
    override val benchOptions: BenchOptions = BenchOptions()

    val ext = DefaultExtraPropertiesExtension()

    fun enabled(value: Boolean?) {
        enabled = value
    }

    fun targetDir(value: String?) {
        targetDir = value
    }

    fun environments(map: Map<String, String>) {
        environments = map
    }

    fun target(value: String?) {
        this.target = value
    }

    fun features(value: Iterable<String>?) {
        this.features = value
    }

    fun targetSelection(closure: Closure<*>) {
        targetSelection.configure(closure)
    }

    fun buildOptions(closure: Closure<*>) {
        buildOptions.configure(closure)
    }

    fun checkOptions(closure: Closure<*>) {
        checkOptions.configure(closure)
    }

    fun testOptions(closure: Closure<*>) {
        testOptions.configure(closure)
    }

    fun benchOptions(closure: Closure<*>) {
        benchOptions.configure(closure)
    }

    fun ext (closure: Closure<*>) {
        closure.resolveStrategy = Closure.DELEGATE_FIRST

        try {
            closure.delegate = ext
            closure.run()
        } catch (ex: MissingPropertyException) {
            closure.delegate = this
            closure.run()
        }
    }
}