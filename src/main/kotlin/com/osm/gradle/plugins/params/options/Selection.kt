package com.osm.gradle.plugins.params.options

import com.osm.gradle.plugins.params.RusticConfigurableBase

class Selection : RusticConfigurableBase() {
    var lib: Boolean? = null
    var bin: Iterable<String?>? = null
    var bins: Boolean? = null
    var example: Iterable<String?>? = null
    var examples: Boolean? = null
    var test: Iterable<String?>? = null
    var tests: Boolean? = null
    var bench: Iterable<String?>? = null
    var benches: Boolean? = null
}