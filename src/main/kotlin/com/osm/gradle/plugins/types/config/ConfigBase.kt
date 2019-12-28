package com.osm.gradle.plugins.types.config

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.IConfigBase
import com.osm.gradle.plugins.types.config.options.BenchOptions
import com.osm.gradle.plugins.types.config.options.BuildOptions
import com.osm.gradle.plugins.types.config.options.CheckOptions
import com.osm.gradle.plugins.types.config.options.Selection
import com.osm.gradle.plugins.types.config.options.TestOptions
import groovy.lang.Closure

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
}