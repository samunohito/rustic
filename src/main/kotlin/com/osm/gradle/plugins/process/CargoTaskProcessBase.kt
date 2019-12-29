package com.osm.gradle.plugins.process

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.util.other.Common
import com.osm.gradle.plugins.wrapper.Cargo
import org.gradle.api.Project

abstract class CargoTaskProcessBase(
    val project: Project,
    val settings: ProjectSettings,
    val variant: BuildVariant
) : IRusticTaskProcess {

    override fun run() {
        if (variant.enabled == true || variant.enabled == null) {
            call(createCargo())
        } else {
            println("The task associated with ${variant.name} has been disabled.")
        }
    }

    private fun createCargo(): Cargo {
        val cargo = Cargo()

        cargo.workingDirectory = Common.getWorkingDirectory(project.projectDir, settings.projectLocation)
        cargo.additionalEnvironment.putAll(variant.environments)

        return cargo
    }

    abstract fun call(cargo: Cargo)
}