package com.osm.gradle.plugins

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.config.BuildTypeConfig
import com.osm.gradle.plugins.types.config.DefaultConfig
import com.osm.gradle.plugins.types.config.ProductFlavorConfig
import com.osm.gradle.plugins.types.variants.BuildVariant
import org.gradle.api.Project
import java.util.concurrent.ConcurrentHashMap

class VariantGenerator(
    private val project: Project,
    private val projectSettings: ProjectSettings,
    private val defaultConfig: DefaultConfig?
) {
    private val buildTypes = ConcurrentHashMap<String, BuildTypeConfig>()
    private val flavors = ConcurrentHashMap<String, ProductFlavorConfig>()
    private val variants = ConcurrentHashMap<String, BuildVariant>()
    private val callbacks = ArrayList<(List<BuildVariant>) -> Unit>()

    fun initialize() {
        buildTypes.clear()
        flavors.clear()
        variants.clear()
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
        buildTypes[buildTypeConfig.name] = buildTypeConfig
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
        flavors[productFlavorConfig.name] = productFlavorConfig
        configure()
    }

    private fun configure() {
        val genVariants = if (flavors.isEmpty()) {
            buildTypes.values.map { BuildVariant(project, projectSettings, defaultConfig, it, null) }
        } else {
            buildTypes.values.flatMap { buildType ->
                flavors.values.map { flavor ->
                    BuildVariant(project, projectSettings, defaultConfig, buildType, flavor)
                }
            }
        }

        val newlyGenVariants = genVariants.filter { !variants.containsKey(it.name) }
        variants.putAll(newlyGenVariants.associateBy { it.name })

        callbacks.forEach { it(newlyGenVariants) }
    }
}