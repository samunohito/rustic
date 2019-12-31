package com.osm.gradle.plugins.types.config

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.config.options.*
import com.osm.gradle.plugins.types.interfaces.IConfigBase
import groovy.lang.Closure
import groovy.lang.MissingPropertyException
import org.gradle.api.NamedDomainObjectFactory
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.ExtensionContainer
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

    open fun enabled(value: Boolean?) {
        enabled = value
    }

    open fun targetDir(value: String?) {
        targetDir = value
    }

    open fun environments(map: Map<String, String>) {
        environments = map
    }

    open fun target(value: String?) {
        this.target = value
    }

    open fun features(value: Iterable<String>?) {
        this.features = value
    }

    open fun targetSelection(closure: Closure<*>) {
        targetSelection.configure(closure)
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

    class Factory<T : ConfigBase>(private val project: Project, private val type: Class<T>) : NamedDomainObjectFactory<T> {
        override fun create(name: String): T {
            return project.extensions.create(name, type, name)
        }
    }
}