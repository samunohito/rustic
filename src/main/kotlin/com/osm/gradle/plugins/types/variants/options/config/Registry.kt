package com.osm.gradle.plugins.types.variants.options.config

import com.osm.gradle.plugins.types.interfaces.options.config.IRegistry

class Registry(args: List<IRegistry?>) : CargoPriorityResolveBase<IRegistry>(args), IRegistry {
    override val index: String?
        get() = resolve { it.index }
    override val default: String?
        get() = resolve { it.default }

    override val environmentPrefix: String
        get() = "REGISTRY"
    override val environmentPropertyMapper: Map<String, () -> Any?>
        get() = mapOf(
            "INDEX" to { index },
            "DEFAULT" to { default }
        )
}