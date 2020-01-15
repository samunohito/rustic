package com.osm.gradle.plugins.types.variants.options.config

import com.osm.gradle.plugins.types.interfaces.options.config.ICargoNew

class CargoNew(args: List<ICargoNew?>) : CargoPriorityResolveBase<ICargoNew>(args), ICargoNew {
    override val name: String?
        get() = resolve { it.name }
    override val email: String?
        get() = resolve { it.email }
    override val vcs: String?
        get() = resolve { it.vcs }

    override val environmentPrefix: String
        get() = "CARGO_NEW"
    override val environmentPropertyMapper: Map<String, () -> Any?>
        get() = mapOf(
            "NAME" to { name },
            "EMAIL" to { email },
            "VCS" to { vcs }
        )
}