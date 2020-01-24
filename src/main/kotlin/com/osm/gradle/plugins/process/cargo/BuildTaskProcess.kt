package com.osm.gradle.plugins.process.cargo

import com.osm.gradle.plugins.process.rustup.TargetAddTaskProcess
import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.wrapper.Cargo
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import org.gradle.api.Project

open class BuildTaskProcess(
    project: Project,
    settings: ProjectSettings,
    variant: BuildVariant
) : CargoTaskProcessBase(project, settings, variant) {
    override fun run() {
        val targetAddTaskProcess = TargetAddTaskProcess(project, settings, variant)
        targetAddTaskProcess.run()

        super.run()
    }

    override fun call(tool: Cargo, builder: OptionBuilder) {
        tool.build(builder)
    }
}
