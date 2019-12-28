package com.osm.gradle.plugins.types.variants.options

import com.osm.gradle.plugins.types.interfaces.options.IBenchOptions
import com.osm.gradle.plugins.types.variants.PriorityResolveBase

class BenchOptions(args: List<IBenchOptions?>) : PriorityResolveBase<IBenchOptions>(args), IBenchOptions {
    override val noRun: Boolean?
        get() = resolve { it.noRun }
    override val noFailFast: Boolean?
        get() = resolve { it.noFailFast }
}