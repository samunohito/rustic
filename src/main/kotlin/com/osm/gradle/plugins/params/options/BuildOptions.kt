package com.osm.gradle.plugins.params.options

import com.osm.gradle.plugins.params.RusticConfigurableBase

class BuildOptions : RusticConfigurableBase() {
    var outputDirectory: String? = null
    var debug: Boolean? = null
}