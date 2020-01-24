package com.osm.gradle.plugins.types.variants.options

import com.osm.gradle.plugins.types.interfaces.options.ICheckOptions
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.types.variants.options.option.Base

class CheckOptions(variant: BuildVariant, args: List<ICheckOptions?>) : Base<ICheckOptions>(variant, args),
    ICheckOptions {
    override val profile: String?
        get() = resolve { it.profile }
    override val all: Boolean?
        get() = resolve { it.all }
    override val allFeatures: Boolean?
        get() = resolve { it.allFeatures }
    override val allTargets: Boolean?
        get() = resolve { it.allTargets }
    override val bench: Iterable<String?>?
        get() = resolve { it.bench }
    override val benches: Boolean?
        get() = resolve { it.benches }
    override val bin: Iterable<String?>?
        get() = resolve { it.bin }
    override val bins: Boolean?
        get() = resolve { it.bins }
    override val example: Iterable<String?>?
        get() = resolve { it.example }
    override val examples: Boolean?
        get() = resolve { it.examples }
    override val exclude: Iterable<String?>?
        get() = resolve { it.exclude }
    override val features: Iterable<String?>?
        get() = resolve { it.features }
    override val lib: Boolean?
        get() = resolve { it.lib }
    override val messageFormat: String?
        get() = resolve { it.messageFormat }
    override val noDefaultFeatures: Boolean?
        get() = resolve { it.noDefaultFeatures }
    override val test: Iterable<String?>?
        get() = resolve { it.test }
    override val tests: Boolean?
        get() = resolve { it.tests }
    override val workspace: Boolean?
        get() = resolve { it.workspace }
    override val jobs: Int?
        get() = resolve { it.jobs }
    override val release: Boolean?
        get() = resolve { it.release }
}