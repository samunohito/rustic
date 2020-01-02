package com.osm.gradle.plugins.process

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.util.other.Common
import com.osm.gradle.plugins.wrapper.RustToolBase
import org.gradle.api.Project

abstract class RusticTaskProcessBase<T : RustToolBase>(
    protected val project: Project,
    protected val settings: ProjectSettings,
    protected val variant: BuildVariant
) : IRusticTaskProcess {
    override fun run() {
        if (variant.enabled == true || variant.enabled == null) {
            val toolBase = createToolBase()

            toolBase.workingDirectory = Common.getWorkingDirectory(project.projectDir, settings.projectLocation)
            toolBase.additionalEnvironment.putAll(variant.environments)

            call(toolBase)
        } else {
            println("The task associated with ${variant.name} has been disabled.")
        }
    }

    /**
     * Create an instance of RustToolBase.
     */
    abstract fun createToolBase(): T

    /**
     * This function is called when RusticTask is executed.
     * Not executed when BuildVariant # enable is false.
     */
    abstract fun call(tool: T)
}