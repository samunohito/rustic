package com.osm.gradle.plugins.params

import com.osm.gradle.plugins.params.options.*
import groovy.lang.Closure

abstract class OptionsBase(val name: String) : ParamBase() {
    var targetDir: String? = null

    val targetSelection: Selection = Selection()
    val buildOptions: BuildOptions = BuildOptions()
    val checkOptions: CheckOptions = CheckOptions()
    val testOptions: TestOptions = TestOptions()
    val benchOptions: BenchOptions = BenchOptions()

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