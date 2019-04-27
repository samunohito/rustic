package com.osm.gradle.plugins.task

import com.osm.gradle.plugins.Rustic
import com.osm.gradle.plugins.TASK_GROUP_NAME
import com.osm.gradle.plugins.params.BuildVariant
import com.osm.gradle.plugins.util.log.info
import com.osm.gradle.plugins.wrapper.Cargo
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Paths

abstract class RustTaskBase : DefaultTask() {
    lateinit var rustic: Rustic
        protected set
    lateinit var variant: BuildVariant
        protected set

    init {
        this.group = TASK_GROUP_NAME
    }

    companion object Factory {
        fun <T : RustTaskBase> create(
            type: Class<T>,
            category: String,
            rustic: Rustic,
            variant: BuildVariant
        ): T {
            val name = "$category${variant.getName()}"
            rustic.project.tasks.removeIf { it.name == name }
            val self = rustic.project.tasks.create(name, type)
            self.rustic = rustic
            self.variant = variant
            return self
        }
    }

    @TaskAction
    fun run() {
        call(createCargo())
    }

    protected fun createCargo(): Cargo {
        info(variant.toString())

        val cargo = Cargo()

        val projectPath = rustic.project.projectDir.toPath().toAbsolutePath()
        cargo.workingDirectory = if (variant.project.projectLocation != null) {
            val path = Paths.get(variant.project.projectLocation)
            if (path.isAbsolute) {
                path
            } else {
                Paths.get(projectPath.toString(), path.toString()).toAbsolutePath()
            }
        } else {
            projectPath
        }

        appendPath(variant.flavor?.toolchainLocations, cargo)

        return cargo
    }

    private fun appendPath(locations: Iterable<String?>?, cargo: Cargo) {
        locations
            ?.filter { it != null }
            ?.map { it!! }
            ?.also {
                val path = it.joinToString(File.pathSeparator) + File.pathSeparator + System.getenv("PATH")
                cargo.additionalEnvironment["PATH"] = path
            }
    }

    abstract fun call(cargo: Cargo)
}