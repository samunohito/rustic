package com.osm.gradle.plugins.types.variants.options

import com.osm.gradle.plugins.types.interfaces.options.ICleanOptions
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.types.variants.options.option.Base

class CleanOptions(variant: BuildVariant, args: List<ICleanOptions?>) : Base<ICleanOptions>(variant, args),
    ICleanOptions {
    override val doc: Boolean?
        get() = resolve { it.doc }
    override val release: Boolean?
        get() = resolve { it.release }
}