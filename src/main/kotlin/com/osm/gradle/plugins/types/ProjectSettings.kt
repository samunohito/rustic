package com.osm.gradle.plugins.types

open class ProjectSettings : RusticConfigurableBase() {
    open var projectLocation: String? = null

    open fun projectLocation(value: String?) {
        projectLocation = value
    }
}