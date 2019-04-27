package com.osm.gradle.plugins.params.options

import com.osm.gradle.plugins.params.ParamBase
import groovy.lang.Closure

class TestOptions : ParamBase() {
    var noRun: Boolean? = null
    var noFailFast: Boolean? = null
    var doc: Boolean? = null
}