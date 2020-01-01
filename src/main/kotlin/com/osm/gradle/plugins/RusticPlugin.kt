package com.osm.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class RusticPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val rustic = Rustic(project)
        project.convention.plugins[EXTENSION_NAME] = rustic
        project.extensions.add(EXTENSION_NAME, rustic)
    }
}
