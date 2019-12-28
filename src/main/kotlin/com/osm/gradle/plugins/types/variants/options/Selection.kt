package com.osm.gradle.plugins.types.variants.options

import com.osm.gradle.plugins.types.interfaces.options.ISelection
import com.osm.gradle.plugins.types.variants.PriorityResolveBase

class Selection(args: List<ISelection?>) : PriorityResolveBase<ISelection>(args), ISelection {
    override val lib: Boolean?
        get() = resolve { it.lib }
    override val bin: Iterable<String?>?
        get() = resolve { it.bin }
    override val bins: Boolean?
        get() = resolve { it.bins }
    override val example: Iterable<String?>?
        get() = resolve { it.example }
    override val examples: Boolean?
        get() = resolve { it.examples }
    override val test: Iterable<String?>?
        get() = resolve { it.test }
    override val tests: Boolean?
        get() = resolve { it.tests }
    override val bench: Iterable<String?>?
        get() = resolve { it.bench }
    override val benches: Boolean?
        get() = resolve { it.benches }
}