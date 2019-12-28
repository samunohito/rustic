package com.osm.gradle.plugins.types.variants.options

import com.osm.gradle.plugins.types.interfaces.options.ITestOptions
import com.osm.gradle.plugins.types.variants.PriorityResolveBase

class TestOptions(args: List<ITestOptions?>) : PriorityResolveBase<ITestOptions>(args), ITestOptions {
    override val noRun: Boolean?
        get() = resolve { it.noRun }
    override val noFailFast: Boolean?
        get() = resolve { it.noFailFast }
    override val doc: Boolean?
        get() = resolve { it.doc }
}