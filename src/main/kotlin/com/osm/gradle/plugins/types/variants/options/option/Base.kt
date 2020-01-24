package com.osm.gradle.plugins.types.variants.options.option

import com.osm.gradle.plugins.types.interfaces.options.option.IBase
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.types.variants.PriorityResolveBase
import com.osm.gradle.plugins.types.variants.options.config.CargoConfig

abstract class Base<T : IBase>(val variant: BuildVariant, args: List<T?>) : PriorityResolveBase<T>(args), IBase {
    override val color: String?
        get() = resolve { it.color }
    override val frozen: Boolean?
        get() = resolve { it.frozen }
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
    override val target: String?
        get() = resolve { it.target }
    override val targetDir: String?
        get() = resolve { it.targetDir }
    override val verbose: Boolean?
        get() = resolve { it.verbose }
    override val cargoConfig: CargoConfig
        get() = CargoConfig(target, targets.map { it?.cargoConfig })
}