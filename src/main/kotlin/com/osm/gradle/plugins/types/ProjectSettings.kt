package com.osm.gradle.plugins.types

class ProjectSettings(val name: String) : RusticConfigurableBase() {
    var projectLocation: String? = null
    var manifestPath: String? = null
    var jobs: Int? = null

    fun projectLocation(value: String?) {
        projectLocation = value
    }

    fun manifestPath(value: String?) {
        manifestPath = value
    }

    fun jobs(value: Int?) {
        jobs = value
    }
}