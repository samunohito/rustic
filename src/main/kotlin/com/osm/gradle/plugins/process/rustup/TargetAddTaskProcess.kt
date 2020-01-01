package com.osm.gradle.plugins.process.rustup

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.wrapper.Rustup
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.helpers.rustup.TargetHelper
import org.gradle.api.Project

open class TargetAddTaskProcess(
    project: Project,
    settings: ProjectSettings,
    variant: BuildVariant
) : RustupTaskProcessBase(project, settings, variant) {
    override fun call(tool: Rustup) {
        val builder = OptionBuilder()

        TargetHelper.AddHelper().put(variant, builder)

        tool.target(builder)
    }
}
