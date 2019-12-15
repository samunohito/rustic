package com.osm.gradle.plugins.params.project

import com.osm.gradle.plugins.params.RusticConfigurableBase
import com.osm.gradle.plugins.params.options.*
import groovy.lang.Closure

abstract class OptionsBase(val name: String) : RusticConfigurableBase() {
    var targetDir: String? = null
    var environments: Map<String, String> = HashMap()
    var target: String? = null
    var features: Iterable<String>? = null

    val targetSelection: Selection = Selection()
    val buildOptions: BuildOptions = BuildOptions()
    val checkOptions: CheckOptions = CheckOptions()
    val testOptions: TestOptions = TestOptions()
    val benchOptions: BenchOptions = BenchOptions()

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