package com.osm.gradle.plugins

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.config.BuildTypeConfig
import com.osm.gradle.plugins.types.config.DefaultConfig
import com.osm.gradle.plugins.types.config.ProductFlavorConfig
import com.osm.gradle.plugins.types.variants.BuildVariant
import org.gradle.api.Project

class VariantGenerator(
    private val project: Project,
    private val projectSettings: ProjectSettings,
    private val defaultConfig: DefaultConfig?
) {
    private val buildTypes = ArrayList<BuildTypeConfig>()
    private val flavors = ArrayList<ProductFlavorConfig>()
    private val callbacks = ArrayList<(List<BuildVariant>) -> Unit>()

    fun initialize() {
        buildTypes.clear()
        flavors.clear()
        callbacks.clear()
    }

    /**
     * Register a callback.
     * The callback is Called when a BuildType or ProductFlavor
     * has been added and a BuildVariant has been created.
     *
     * @param callback
     */
    fun addCallback(callback: (List<BuildVariant>) -> Unit) {
        if (callbacks.contains(callback)) {
            return
        }

        callbacks.add(callback)
    }

    /**
     * Register BuildType.
     * Calling this function invokes the callback.
     *
     * @see addCallback
     * @param buildTypeConfig
     */
    fun addBuildType(buildTypeConfig: BuildTypeConfig) {
        buildTypes.add(buildTypeConfig)
        configure()
    }

    /**
     * Register ProductFlavor.
     * Calling this function invokes the callback.
     *
     * @see addCallback
     * @param productFlavorConfig
     */
    fun addProductFlavor(productFlavorConfig: ProductFlavorConfig) {
        flavors.add(productFlavorConfig)
        configure()
    }

    @Synchronized
    private fun configure() {
        val ret = if (flavors.isEmpty()) {
            buildTypes.map { BuildVariant(project, projectSettings, defaultConfig, it, null) }
        } else {
            buildTypes.flatMap { buildType ->
                flavors.map { flavor ->
                    BuildVariant(project, projectSettings, defaultConfig, buildType, flavor)
                }
            }
        }

        callbacks.forEach { it(ret) }
    }
}