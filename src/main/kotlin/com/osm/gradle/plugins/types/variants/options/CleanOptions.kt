package com.osm.gradle.plugins.types.variants.options

import com.osm.gradle.plugins.types.interfaces.options.ICleanOptions
import com.osm.gradle.plugins.types.variants.PriorityResolveBase

class CleanOptions(args: List<ICleanOptions>) : PriorityResolveBase<ICleanOptions>(args), ICleanOptions {
    override val doc: Boolean?
        get() = resolve { it.doc }
    override val release: Boolean?
        get() = resolve { it.release }
    override val targetDir: String?
        get() = resolve { it.targetDir }
    override val triple: String?
        get() = resolve { it.triple }
}