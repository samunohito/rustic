package com.osm.gradle.plugins.types.config.options

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.ISelection

class Selection : RusticConfigurableBase(), ISelection {
    override var lib: Boolean? = null
    override var bin: Iterable<String?>? = null
    override var bins: Boolean? = null
    override var example: Iterable<String?>? = null
    override var examples: Boolean? = null
    override var test: Iterable<String?>? = null
    override var tests: Boolean? = null
    override var bench: Iterable<String?>? = null
    override var benches: Boolean? = null
}