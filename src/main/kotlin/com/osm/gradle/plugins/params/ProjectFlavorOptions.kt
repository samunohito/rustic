package com.osm.gradle.plugins.params

class ProjectFlavorOptions(name: String) : OptionsBase(name) {
    var triple: String? = null
    var toolchainLocations: Iterable<String>? = null
    var features: Iterable<String>? = null
}