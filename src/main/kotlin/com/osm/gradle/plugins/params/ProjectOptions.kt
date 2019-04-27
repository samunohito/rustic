package com.osm.gradle.plugins.params

class ProjectOptions(name: String) : OptionsBase(name) {
    var projectLocation: String? = null
    var manifestPath: String? = null
    var jobs: Int? = null
}