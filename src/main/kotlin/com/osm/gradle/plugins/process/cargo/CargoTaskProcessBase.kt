package com.osm.gradle.plugins.process.cargo

import com.osm.gradle.plugins.process.RusticTaskProcessBase
import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.interfaces.options.option.IBase
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.types.variants.options.option.Base
import com.osm.gradle.plugins.wrapper.Cargo
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.OptionHelper
import org.gradle.api.Project

abstract class CargoTaskProcessBase<T : Base<*>>(
    project: Project,
    settings: ProjectSettings,
    variant: BuildVariant
) : RusticTaskProcessBase<Cargo, T>(project, settings, variant) {
    override fun putOptions(builder: OptionBuilder) {
        OptionHelper.put(options, builder)
    }

    override fun createToolBase(): Cargo {
        val cargo = Cargo()

        val config = options.cargoConfig.getConfigMap()
        cargo.additionalEnvironment.putAll(config)

        debug("[config envs]")
        config.forEach {
            debug("  ${it.key} : ${it.value}")
        }

        return cargo
    }
}