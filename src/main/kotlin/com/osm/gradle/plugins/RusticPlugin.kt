package com.osm.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class RusticPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create(EXTENSION_NAME, Rustic::class.java, project)
    }
}