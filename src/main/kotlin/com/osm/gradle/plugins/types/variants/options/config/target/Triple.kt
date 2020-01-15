package com.osm.gradle.plugins.types.variants.options.config.target

import com.osm.gradle.plugins.types.interfaces.options.config.target.ITriple
import com.osm.gradle.plugins.types.variants.options.config.CargoPriorityResolveBase

class Triple(val target: String?, args: List<ITriple?>) : CargoPriorityResolveBase<ITriple>(args), ITriple {
    override val linker: String?
        get() = resolve { it.linker }
    override val ar: String?
        get() = resolve { it.ar }
    override val runner: String?
        get() = resolve { it.runner }
    override val rustFlags: Iterable<String?>?
        get() = resolve { it.rustFlags }

    override val environmentPrefix: String
        get() = if (target != null) "TARGET_" + target.replace("-", "_").toUpperCase() else ""
    override val environmentPropertyMapper: Map<String, () -> Any?>
        get() = mapOf(
            "LINKER" to { if (target != null) linker else null },
            "AR" to { if (target != null) ar else null },
            "RUNNER" to { if (target != null) runner else null },
            "RUSTFLAGS" to { if (target != null) rustFlags?.joinToString(" ") else null }
        )
}