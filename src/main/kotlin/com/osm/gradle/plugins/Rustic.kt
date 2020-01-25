package com.osm.gradle.plugins

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.config.BuildTypeConfig
import com.osm.gradle.plugins.types.config.ConfigBase
import com.osm.gradle.plugins.types.config.DefaultConfig
import com.osm.gradle.plugins.types.config.ProductFlavorConfig
import com.osm.gradle.plugins.types.variants.BuildVariant
import groovy.lang.Closure
import groovy.lang.GroovyObjectSupport
import org.gradle.api.DomainObjectSet
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.internal.CollectionCallbackActionDecorator
import org.gradle.api.internal.DefaultDomainObjectSet
import org.gradle.api.plugins.ExtensionAware

open class Rustic(val name: String, val project: Project) : GroovyObjectSupport() {
    val projectSettings: ProjectSettings
    val defaultConfig: DefaultConfig
    val buildTypes: NamedDomainObjectContainer<BuildTypeConfig>
    val flavors: NamedDomainObjectContainer<ProductFlavorConfig>
    val variants: DomainObjectSet<BuildVariant>

    private val listener: RusticProjectEvaluationListener

    init {
        // ----------------------------------------------------------------
        // initialize members
        // ----------------------------------------------------------------

        val extensions = (project as ExtensionAware).extensions

        projectSettings = extensions.create("projectSettings", ProjectSettings::class.java)
        defaultConfig = extensions.create("defaultConfig", DefaultConfig::class.java)
        buildTypes = project.container(
            BuildTypeConfig::class.java,
            ConfigBase.Factory(extensions, BuildTypeConfig::class.java)
        )
        flavors = project.container(
            ProductFlavorConfig::class.java,
            ConfigBase.Factory(extensions, ProductFlavorConfig::class.java)
        )
        variants = DefaultDomainObjectSet<BuildVariant>(
            BuildVariant::class.java,
            CollectionCallbackActionDecorator.NOOP
        )

        extensions.add("buildTypes", buildTypes)
        extensions.add("flavors", flavors)
        extensions.add("variants", variants)

        listener = RusticProjectEvaluationListener(this)
        project.gradle.addProjectEvaluationListener(listener)

        // ----------------------------------------------------------------
        // set default buildTypes
        // ----------------------------------------------------------------

        val debugOptions = ConfigBase.Factory(extensions, BuildTypeConfig::class.java).create("debug")
        debugOptions.buildOptions.release = false
        debugOptions.cleanOptions.release = false
        debugOptions.testOptions.release = false
        debugOptions.cleanOptions.release = false
        buildTypes.add(debugOptions)

        val releaseOptions = ConfigBase.Factory(extensions, BuildTypeConfig::class.java).create("release")
        releaseOptions.buildOptions.release = true
        releaseOptions.cleanOptions.release = true
        releaseOptions.testOptions.release = true
        releaseOptions.cleanOptions.release = true
        buildTypes.add(releaseOptions)
    }

    /**
     * Make settings that affect the entire configuration.
     * You can set the working directory, number of jobs, and manifest path.
     *
     * @param closure
     */
    fun projectSettings(closure: Closure<*>) {
        projectSettings.configure(closure)
    }

    /**
     * Make settings that affect the build artifacts.
     * In addition, you can set targetSelection, buildOptions, checkOptions, testOptions, and benchOptions.
     * (The settings of buildTypes and productFlavors take precedence)
     *
     * @param closure
     */
    fun defaultConfig(closure: Closure<*>) {
        defaultConfig.configure(closure)
    }

    /**
     * Make settings that affect the build artifacts.
     * In addition, you can set targetSelection, buildOptions, checkOptions, testOptions, and benchOptions.
     * (takes precedence over defaultConfig, but productFlavors takes precedence over buildTypes)
     *
     * @param closure
     */
    fun buildTypes(closure: Closure<*>) {
        buildTypes.configure(closure)
    }

    /**
     * You can configure the toolchain (x86_64-linux-gnu-gcc and related tools) to be used in triple and build.
     * In addition, you can set targetSelection, buildOptions, checkOptions, testOptions, and benchOptions.
     *
     * @param closure
     */
    fun flavors(closure: Closure<*>) {
        flavors.configure(closure)
    }
}