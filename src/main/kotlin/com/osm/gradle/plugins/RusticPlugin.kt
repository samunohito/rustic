package com.osm.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class RusticPlugin : Plugin<Project> {
    /**
     * initialize Rustic.
     */
    override fun apply(project: Project) {
        val rustic = project.extensions.create(EXTENSION_NAME, Rustic::class.java, EXTENSION_NAME, project)
        project.convention.plugins[EXTENSION_NAME] = rustic
    }
}
