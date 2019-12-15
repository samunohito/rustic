package com.osm.gradle.plugins.params.options

import com.osm.gradle.plugins.params.RusticConfigurableBase

class TestOptions : RusticConfigurableBase() {
    var noRun: Boolean? = null
    var noFailFast: Boolean? = null
    var doc: Boolean? = null
}