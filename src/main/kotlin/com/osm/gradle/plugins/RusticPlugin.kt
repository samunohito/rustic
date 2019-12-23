package com.osm.gradle.plugins

import com.osm.gradle.plugins.params.BuildVariant
import com.osm.gradle.plugins.params.project.ProjectBuildOptions
import com.osm.gradle.plugins.process.RusticTaskProcessBase
import groovy.lang.Closure
import org.gradle.api.Plugin
import org.gradle.api.Project

class RusticPlugin : Plugin<Project> {
    lateinit var rustic: Rustic

    override fun apply(project: Project) {
        rustic = Rustic(project)
        project.convention.plugins[EXTENSION_NAME] = rustic
        project.extensions.add(EXTENSION_NAME, rustic)

        rustic.createVariants()
    }
}


val DEBUG = true
fun log(msg: String) {
    if (DEBUG) {
        println(msg)
    }
}