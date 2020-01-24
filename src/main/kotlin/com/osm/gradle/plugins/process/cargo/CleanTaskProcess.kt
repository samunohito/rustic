package com.osm.gradle.plugins.process.cargo

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.interfaces.options.ICleanOptions
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.wrapper.Cargo
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import org.gradle.api.Project

open class CleanTaskProcess(
    project: Project,
    settings: ProjectSettings,
    variant: BuildVariant,
    val options: ICleanOptions
) :
    CargoTaskProcessBase(project, settings, variant) {
    override fun call(tool: Cargo, builder: OptionBuilder) {
        tool.clean(builder)
    }
}
