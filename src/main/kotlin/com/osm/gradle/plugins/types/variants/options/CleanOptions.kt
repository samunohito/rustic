package com.osm.gradle.plugins.types.variants.options

import com.osm.gradle.plugins.types.interfaces.options.ICleanOptions
import com.osm.gradle.plugins.types.variants.PriorityResolveBase

class CleanOptions(args: List<ICleanOptions?>) : PriorityResolveBase<ICleanOptions>(args), ICleanOptions {
    override val color: String?
        get() = resolve { it.color }
    override val doc: Boolean?
        get() = resolve { it.doc }
    override val frozen: Boolean?
        get() = resolve { it.frozen }
    override val help: Boolean?
        get() = resolve { it.help }
    override val locked: Boolean?
        get() = resolve { it.locked }
    override val manifestPath: String?
        get() = resolve { it.manifestPath }
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
    override val verbose: Boolean?
        get() = resolve { it.verbose }

}