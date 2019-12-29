package com.osm.gradle.plugins.types

open class ProjectSettings : RusticConfigurableBase() {
    open var projectLocation: String? = null
    open var manifestPath: String? = null
    open var jobs: Int? = null

    open fun projectLocation(value: String?) {
        projectLocation = value
    }

    open fun manifestPath(value: String?) {
        manifestPath = value
    }

    open fun jobs(value: Int?) {
        jobs = value
    }
}