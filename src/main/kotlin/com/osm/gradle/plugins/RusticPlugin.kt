package com.osm.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class RusticPlugin : Plugin<Project> {
    lateinit var rustic: Rustic

    override fun apply(project: Project) {
        rustic = Rustic(project)
        project.convention.plugins[EXTENSION_NAME] = rustic
        project.extensions.add(EXTENSION_NAME, rustic)
    }
}
