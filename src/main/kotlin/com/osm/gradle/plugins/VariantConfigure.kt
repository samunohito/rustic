package com.osm.gradle.plugins

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.config.BuildTypeConfig
import com.osm.gradle.plugins.types.config.DefaultConfig
import com.osm.gradle.plugins.types.config.ProductFlavorConfig
import com.osm.gradle.plugins.types.variants.BuildVariant
import org.gradle.api.Action
import org.gradle.api.Project

class VariantConfigure(
    private val project: Project,
    private val projectSettings: ProjectSettings,
    private val defaultConfig: DefaultConfig?
) {
    private val buildTypes = ArrayList<BuildTypeConfig>()
    private val flavors = ArrayList<ProductFlavorConfig>()
    private val callbacks = ArrayList<Action<List<BuildVariant>>>()

    fun addCallback(callback: Action<List<BuildVariant>>) {
        if (callbacks.contains(callback)) {
            return
        }

        callbacks.add(callback)
    }

    fun removeCallback(callback: Action<List<BuildVariant>>) {
        if (!callbacks.contains(callback)) {
            return
        }

        callbacks.remove(callback)
    }

    fun clearCallbacks() {
        callbacks.clear()
    }

    fun addBuildType(buildTypeConfig: BuildTypeConfig) {
        buildTypes.add(buildTypeConfig)
        configure()
    }

    fun addFlavor(productFlavorConfig: ProductFlavorConfig) {
        flavors.add(productFlavorConfig)
        configure()
    }

    fun clearBuildTypes() {
        buildTypes.clear()
    }

    fun clearFlavors() {
        flavors.clear()
    }

    private fun configure() {
        val tmp = if (flavors.isEmpty()) {
            buildTypes.map { BuildVariant(project, projectSettings, defaultConfig, it, null) }
        } else {
            buildTypes.flatMap { buildType ->
                flavors.map { flavor ->
                    BuildVariant(project, projectSettings, defaultConfig, buildType, flavor)
                }
            }
        }

        val ret = tmp.filter { it.enabled ?: true }
        callbacks.forEach { it.execute(ret) }
    }
}