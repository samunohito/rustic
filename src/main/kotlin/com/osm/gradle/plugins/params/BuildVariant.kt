package com.osm.gradle.plugins.params

import com.osm.gradle.plugins.params.project.ProjectBuildOptions
import com.osm.gradle.plugins.params.project.ProjectDefaultOptions
import com.osm.gradle.plugins.params.project.ProjectFlavorOptions

class BuildVariant(
    val project: ProjectSettings,
    val default: ProjectDefaultOptions?,
    val build: ProjectBuildOptions?,
    val flavor: ProjectFlavorOptions?
) {
    fun getOptionsNonNull() = listOfNotNull(default, build, flavor)
    fun getName() = listOfNotNull(build, flavor).joinToString("") { it.name.capitalize() }

    constructor(project: ProjectSettings, default: ProjectDefaultOptions?) : this(project, default, null, null);

    override fun toString(): String {
        return StringBuilder()
            .append("project = $project").append("\n")
            .append("default   = " + default.toString()).append("\n")
            .append("build   = " + build.toString()).append("\n")
            .append("flavor  = " + flavor.toString()).append("\n")
            .toString()
    }
}