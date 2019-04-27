package com.osm.gradle.plugins.params

class BuildVariant(
    val project: ProjectOptions,
    val build: ProjectBuildOptions?,
    val flavor: ProjectFlavorOptions?
) : ParamBase() {
    fun getOptionsNonNull() = listOfNotNull(project, build, flavor)
    fun getName() = getOptionsNonNull().joinToString("") { it.name.capitalize() }

    override fun toString(): String {
        return StringBuilder()
            .append("project = $project").append("\n")
            .append("build   = " + build.toString()).append("\n")
            .append("flavor  = " + flavor.toString()).append("\n")
            .toString()
    }
}