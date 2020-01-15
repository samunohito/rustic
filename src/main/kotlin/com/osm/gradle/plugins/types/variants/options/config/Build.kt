package com.osm.gradle.plugins.types.variants.options.config

import com.osm.gradle.plugins.types.interfaces.options.config.IBuild

class Build(args: List<IBuild?>) : CargoPriorityResolveBase<IBuild>(args), IBuild {
    override val jobs: Int?
        get() = resolve { it.jobs }
    override val rustc: String?
        get() = resolve { it.rustc }
    override val rustcWrapper: String?
        get() = resolve { it.rustcWrapper }
    override val rustDoc: String?
        get() = resolve { it.rustDoc }
    override val target: String?
        get() = resolve { it.target }
    override val targetDir: String?
        get() = resolve { it.targetDir }
    override val rustFlags: Iterable<String?>?
        get() = resolve { it.rustFlags }
    override val rustDocFlags: Iterable<String?>?
        get() = resolve { it.rustDocFlags }
    override val incremental: Boolean?
        get() = resolve { it.incremental }
    override val depInfoBaseDir: String?
        get() = resolve { it.depInfoBaseDir }

    override val environmentPrefix: String
        get() = "BUILD"
    override val environmentPropertyMapper: Map<String, () -> Any?>
        get() = mapOf(
            "JOBS" to { jobs },
            "RUSTC" to { rustc },
            "RUSTC_WRAPPER" to { rustcWrapper },
            "RUSTDOC" to { rustDoc },
            "TARGET" to { target },
            "TARGET_DIR" to { targetDir },
            "RUSTFLAGS" to { rustFlags },
            "RUSTDOCFLAGS" to { rustDocFlags },
            "INCREMENTAL" to { incremental },
            "DEP_INFO_BASEDIR" to { depInfoBaseDir }
        )
}