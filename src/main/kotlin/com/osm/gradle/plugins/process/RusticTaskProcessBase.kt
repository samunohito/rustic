package com.osm.gradle.plugins.process

import com.osm.gradle.plugins.log.LoggerSupport
import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.interfaces.options.option.IBase
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.util.other.Common
import com.osm.gradle.plugins.wrapper.RustToolBase
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import org.gradle.api.Project

abstract class RusticTaskProcessBase<T : RustToolBase, U : IBase>(
    protected val project: Project,
    protected val settings: ProjectSettings,
    protected val variant: BuildVariant
) : IRusticTaskProcess<U>, LoggerSupport {
    override fun run() {
        if (variant.enabled == false) {
            info("The task associated with ${variant.name} has been disabled.")
            return
        }

        val toolBase = createToolBase()

        toolBase.workingDirectory = Common.getWorkingDirectory(project.projectDir, settings.projectLocation)
        debug("[workingDirectory] ${toolBase.workingDirectory}")

        if (!variant.environments.isNullOrEmpty()) {
            toolBase.additionalEnvironment.putAll(
                variant.environments!!
                    .filter { it.key != null && it.value != null }
                    .mapKeys { it.key!! }
                    .mapValues { it.value!! }
            )

            debug("[environments]")
            variant.environments!!.forEach {
                debug("  ${it.key} : ${it.value}")
            }
        }

        val builder = OptionBuilder()
        putOptions(builder)
        call(toolBase, builder)
    }

    protected open fun putOptions(builder: OptionBuilder) {

    }

    /**
     * Create an instance of RustToolBase.
     */
    protected abstract fun createToolBase(): T

    /**
     * This function is called when RusticTask is executed.
     * Not executed when BuildVariant # enable is false.
     */
    protected abstract fun call(tool: T, builder: OptionBuilder)
}