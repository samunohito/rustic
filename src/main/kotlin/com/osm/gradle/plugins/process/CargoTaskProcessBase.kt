package com.osm.gradle.plugins.process

import com.osm.gradle.plugins.Rustic
import com.osm.gradle.plugins.params.BuildVariant
import com.osm.gradle.plugins.util.log.info
import com.osm.gradle.plugins.wrapper.Cargo
import java.nio.file.Paths

abstract class CargoTaskProcessBase(rustic: Rustic, variant: BuildVariant) : RusticTaskProcessBase(rustic, variant) {
    override fun run() {
        call(createCargo())
    }

    private fun createCargo(): Cargo {
        info(variant.toString())

        val cargo = Cargo()

        val projectPath = rustic.project.projectDir.toPath().toAbsolutePath()
        val location = variant.project.projectLocation
        if (location != null) {
            val path = Paths.get(location)
            cargo.workingDirectory = if (path.isAbsolute) {
                path
            } else {
                Paths.get(projectPath.toString(), path.toString()).toAbsolutePath()
            }
        } else {
            cargo.workingDirectory = projectPath
        }

        variant.default?.also { cargo.additionalEnvironment.putAll(it.environments) }
        variant.build?.also { cargo.additionalEnvironment.putAll(it.environments) }
        variant.flavor?.also { cargo.additionalEnvironment.putAll(it.environments) }

        return cargo
    }

    abstract fun call(cargo: Cargo)
}