package com.osm.gradle.pSelectionlugins.types.variants.options

import com.osm.gradle.plugins.types.interfaces.options.ICheckOptions
import com.osm.gradle.plugins.types.variants.PriorityResolveBase

class CheckOptions(args: List<ICheckOptions?>) : PriorityResolveBase<ICheckOptions>(args), ICheckOptions {
    override val profile: String?
        get() = resolve { it.profile }
}