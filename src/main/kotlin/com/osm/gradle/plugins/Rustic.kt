package com.osm.gradle.plugins

import com.osm.gradle.plugins.task.RusticTask
import com.osm.gradle.plugins.task.TaskGenerator
import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.config.BuildTypeConfig
import com.osm.gradle.plugins.types.config.DefaultConfig
import com.osm.gradle.plugins.types.config.ProductFlavorConfig
import com.osm.gradle.plugins.types.variants.BuildVariant
import groovy.lang.Closure
import org.gradle.api.Action
import org.gradle.api.DomainObjectSet
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.internal.CollectionCallbackActionDecorator
import org.gradle.api.internal.DefaultDomainObjectSet

/**
 * https://mrhaki.blogspot.com/2016/02/gradle-goodness-using-nested-domain.html
 * https://mrhaki.blogspot.com/2016/02/gradle-goodness-create-objects-with-dsl.html
 * https://henteko07.hatenablog.com/entry/2013/11/29/070537
 */
open class Rustic(val project: Project) {
    val defaultConfig: DefaultConfig =
        DefaultConfig()
    val buildTypes: NamedDomainObjectContainer<BuildTypeConfig> =
        project.container(BuildTypeConfig::class.java)
    val flavors: NamedDomainObjectContainer<ProductFlavorConfig> =
        project.container(ProductFlavorConfig::class.java)
    val projectSettings: ProjectSettings =
        ProjectSettings("")

    val variants: DomainObjectSet<BuildVariant> =
        DefaultDomainObjectSet<BuildVariant>(BuildVariant::class.java, CollectionCallbackActionDecorator.NOOP)

    private val taskGenerator = TaskGenerator(project, projectSettings)
    private val variantManager = VariantManager(projectSettings, defaultConfig)

    init {
        variantManager.clearCallbacks()
        variantManager.clearBuildTypes()
        variantManager.clearFlavors()

        RusticTask.disableAll(project.tasks)

        val debugOptions = BuildTypeConfig("debug")
        debugOptions.buildOptions.debug = true
        buildTypes.add(debugOptions)

        val releaseOptions = BuildTypeConfig("release")
        releaseOptions.buildOptions.debug = false
        buildTypes.add(releaseOptions)

        buildTypes.all { buildType ->
            variantManager.addBuildType(buildType)
        }

        flavors.all { flavor ->
            variantManager.addFlavor(flavor)
        }

        variantManager.addCallback(Action {
            taskGenerator.createTasks(it)
            variants.clear()
            variants.addAll(it)
        })
    }

    /**
     * Make settings that affect the entire config.
     * In addition to config configuration such as config location and manifest path,
     * You can also set default values​for buildType and the following items that can be set for flavors.
     * (the settings of buildType and config take precedence)
     * targetSelection, buildOptions, checkOptions, testOptions, and benchOptions.
     */
    fun projectSettings(closure: Closure<*>) {
        projectSettings.configure(closure)
    }

    /**
     * TODO:document
     */
    fun defaultConfig(closure: Closure<*>) {
        defaultConfig.configure(closure)
    }

    /**
     * Make settings that affect the build artifacts.
     * In addition, you can set targetSelection, buildOptions, checkOptions, testOptions, and benchOptions.
     * (The settings of flavors take precedence)
     */
    fun buildTypes(closure: Closure<*>) {
        buildTypes.configure(closure)
    }

    /**
     * You can configure the toolchain (x86_64-linux-gnu-gcc and related tools) to be used in triple and build.
     * In addition, you can set targetSelection, buildOptions, checkOptions, testOptions, and benchOptions.
     */
    fun flavors(closure: Closure<*>) {
        flavors.configure(closure)
    }
}