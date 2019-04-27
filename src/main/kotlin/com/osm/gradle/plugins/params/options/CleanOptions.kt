package com.osm.gradle.plugins.params.options

import com.osm.gradle.plugins.params.ParamBase

class CleanOptions : ParamBase() {
    var doc: Boolean? = null
    var release: Boolean? = null
    var targetDir: String? = null
    var triple: String? = null
}