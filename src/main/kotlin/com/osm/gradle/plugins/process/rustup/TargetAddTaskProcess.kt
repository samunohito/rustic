package com.osm.gradle.plugins.process.rustup

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.interfaces.options.option.IBase
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.wrapper.Rustup
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import org.gradle.api.Project

open class TargetAddTaskProcess(
    project: Project,
    settings: ProjectSettings,
    variant: BuildVariant,
    override val options: IBase
) : RustupTaskProcessBase(project, settings, variant) {
    override fun call(tool: Rustup, builder: OptionBuilder) {
        tool.target(builder)
    }
}
