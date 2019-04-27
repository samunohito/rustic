package com.osm.gradle.plugins.params.options

import com.osm.gradle.plugins.params.ParamBase
import groovy.lang.Closure

class BenchOptions : ParamBase() {
    var noRun: Boolean? = null
    var noFailFast: Boolean? = null
}