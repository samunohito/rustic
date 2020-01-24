package com.osm.gradle.plugins.process.cargo

import com.osm.gradle.plugins.process.RusticTaskProcessBase
import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.interfaces.options.option.IBase
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.wrapper.Cargo
import org.gradle.api.Project

abstract class CargoTaskProcessBase<T : IBase>(
    project: Project,
    settings: ProjectSettings,
    variant: BuildVariant
) : RusticTaskProcessBase<Cargo, T>(project, settings, variant) {
    override fun createToolBase() = Cargo()
}