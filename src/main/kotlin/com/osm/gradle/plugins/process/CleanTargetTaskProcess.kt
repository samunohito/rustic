package com.osm.gradle.plugins.process

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.interfaces.options.ICleanOptions
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.wrapper.Cargo
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.helpers.CleanOptionsHelper
import org.gradle.api.Project

open class CleanTargetTaskProcess(
    project: Project,
    settings: ProjectSettings,
    variant: BuildVariant,
    val options: ICleanOptions
) :
    CargoTaskProcessBase(project, settings, variant) {
    override fun call(cargo: Cargo) {
        val builder = OptionBuilder()
        CleanOptionsHelper().put(options, builder)
        cargo.clean(builder)
    }
}
