package com.osm.gradle.plugins.params.options

import com.osm.gradle.plugins.params.ParamBase
import groovy.lang.Closure

class BuildOptions : ParamBase() {
    var outputDirectory: String? = null
    var debug: Boolean? = null
}