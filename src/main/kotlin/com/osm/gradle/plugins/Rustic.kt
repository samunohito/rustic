package com.osm.gradle.plugins

import com.osm.gradle.plugins.task.RusticTask
import com.osm.gradle.plugins.task.TaskGenerator
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

open class Rustic(val name: String, project: Project) : GroovyObjectSupport() {
    val projectSettings: ProjectSettings
    val defaultConfig: DefaultConfig
    val buildTypes: NamedDomainObjectContainer<BuildTypeConfig>
    val flavors: NamedDomainObjectContainer<ProductFlavorConfig>
    val variants: DomainObjectSet<BuildVariant>

    private val taskGenerator: TaskGenerator
    private val variantGenerator: VariantGenerator

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

        taskGenerator = TaskGenerator(project, projectSettings)
        variantGenerator = VariantGenerator(project, projectSettings, defaultConfig)

        // ----------------------------------------------------------------
        // initialize variants and tasks
        // ----------------------------------------------------------------

        RusticTask.disableAll(project.tasks)
        taskGenerator.createCleanTasks()

        variantGenerator.initialize()
        variantGenerator.addCallback {
            taskGenerator.createVariantTasks(TaskGenerator.RequestItem(it) {
                variants.addAll(it)
            })
        }

        buildTypes.all {
            variantGenerator.addBuildType(it)
        }

        flavors.all {
            variantGenerator.addProductFlavor(it)
        }

        // ----------------------------------------------------------------
        // set default buildTypes
        // ----------------------------------------------------------------

        val debugOptions = ConfigBase.Factory(extensions, BuildTypeConfig::class.java).create("debug")
        debugOptions.buildOptions.debug = true
        buildTypes.add(debugOptions)

        val releaseOptions = ConfigBase.Factory(extensions, BuildTypeConfig::class.java).create("release")
        releaseOptions.buildOptions.debug = false
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