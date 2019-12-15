package com.osm.gradle.plugins.params.options

import com.osm.gradle.plugins.params.RusticConfigurableBase

class CleanOptions : RusticConfigurableBase() {
    var doc: Boolean? = null
    var release: Boolean? = null
    var targetDir: String? = null
    var triple: String? = null
}