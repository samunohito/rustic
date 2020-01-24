package com.osm.gradle.plugins.types.variants.options

import com.osm.gradle.plugins.types.interfaces.options.ITestOptions
import com.osm.gradle.plugins.types.variants.PriorityResolveBase

class TestOptions(args: List<ITestOptions?>) : PriorityResolveBase<ITestOptions>(args), ITestOptions {
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
    override val color: String?
        get() = resolve { it.color }
    override val doc: Boolean?
        get() = resolve { it.doc }
    override val example: Iterable<String?>?
        get() = resolve { it.example }
    override val examples: Boolean?
        get() = resolve { it.examples }
    override val exclude: Iterable<String?>?
        get() = resolve { it.exclude }
    override val features: String?
        get() = resolve { it.features }
    override val frozen: Boolean?
        get() = resolve { it.frozen }
    override val help: Boolean?
        get() = resolve { it.help }
    override val lib: Boolean?
        get() = resolve { it.lib }
    override val locked: Boolean?
        get() = resolve { it.locked }
    override val manifestPath: String?
        get() = resolve { it.manifestPath }
    override val messageFormat: String?
        get() = resolve { it.messageFormat }
    override val noDefaultFeatures: Boolean?
        get() = resolve { it.noDefaultFeatures }
    override val noFailFast: Boolean?
        get() = resolve { it.noFailFast }
    override val noRun: Boolean?
        get() = resolve { it.noRun }
    override val offline: Boolean?
        get() = resolve { it.offline }
    override val packageName: Iterable<String?>?
        get() = resolve { it.packageName }
    override val quiet: Boolean?
        get() = resolve { it.quiet }
    override val release: Boolean?
        get() = resolve { it.release }
    override val target: String?
        get() = resolve { it.target }
    override val targetDir: String?
        get() = resolve { it.targetDir }
    override val test: Iterable<String?>?
        get() = resolve { it.test }
    override val tests: Boolean?
        get() = resolve { it.tests }
    override val verbose: Boolean?
        get() = resolve { it.verbose }
    override val workspace: Boolean?
        get() = resolve { it.workspace }
}