package com.osm.gradle.plugins.types.variants.options.config

import com.osm.gradle.plugins.types.interfaces.options.config.ITerm

class Term(args: List<ITerm?>) : CargoPriorityResolveBase<ITerm>(args), ITerm {
    override val verbose: Boolean?
        get() = resolve { it.verbose }
    override val color: String?
        get() = resolve { it.color }

    override val environmentPrefix: String
        get() = "TERM"
    override val environmentPropertyMapper: Map<String, () -> Any?>
        get() = mapOf(
            "VERBOSE" to { verbose },
            "COLOR" to { color }
        )
}