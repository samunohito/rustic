package com.osm.gradle.plugins.types.variants.options

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.util.other.Common
import org.gradle.api.Project
import java.nio.file.Path
import java.nio.file.Paths

object OptionsUtil {
    fun getOutputPath(
        target: String?,
        targetDir: String?,
        isRelease: Boolean?,
        project: Project,
        settings: ProjectSettings
    ): Path {
        val intermediates = if (targetDir != null) {
            Paths.get(targetDir)
        } else {
            Paths.get(Common.getWorkingDirectory(project.projectDir, settings.projectLocation).toString(), "target")
        }

        val buildTypeString = if (isRelease == true) "release" else "debug"

        return if (target != null) {
            Paths.get(intermediates.toString(), target, buildTypeString)
        } else {
            Paths.get(intermediates.toString(), buildTypeString)
        }
    }
}