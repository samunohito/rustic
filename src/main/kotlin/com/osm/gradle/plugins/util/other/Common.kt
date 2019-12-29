package com.osm.gradle.plugins.util.other

import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

object Common {
    fun getWorkingDirectory(projectDir: File, projectLocation: String?): Path {
        val projectPath = projectDir.toPath().toAbsolutePath()
        val location = projectLocation?.let { Paths.get(it) }

        return if (location != null) {
            if (location.isAbsolute) {
                location
            } else {
                Paths.get(projectPath.toString(), location.toString()).toAbsolutePath()
            }
        } else {
            projectPath
        }
    }
}