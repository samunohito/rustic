package com.osm.gradle.plugins.process

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.wrapper.Cargo
import org.gradle.api.Project
import java.nio.file.Paths

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

        val projectPath = project.projectDir.toPath().toAbsolutePath()
        val location = settings.projectLocation?.let { Paths.get(it) }

        val workingDirectory = if (location != null) {
            if (location.isAbsolute) {
                location
            } else {
                Paths.get(projectPath.toString(), location.toString()).toAbsolutePath()
            }
        } else {
            projectPath
        }

        cargo.workingDirectory = workingDirectory
        cargo.additionalEnvironment.putAll(variant.environments)

        return cargo
    }

    abstract fun call(cargo: Cargo)
}