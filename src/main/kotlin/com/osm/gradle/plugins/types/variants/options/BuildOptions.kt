package com.osm.gradle.plugins.types.variants.options

import com.osm.gradle.plugins.types.interfaces.options.IBuildOptions
import com.osm.gradle.plugins.types.variants.PriorityResolveBase

class BuildOptions(args: List<IBuildOptions?>) : PriorityResolveBase<IBuildOptions>(args), IBuildOptions {
    override val outputDirectory: String?
        get() = resolve { it.outputDirectory }
    override val debug: Boolean?
        get() = resolve { it.debug }
}