package com.osm.gradle.plugins.types.variants.options.config

import com.osm.gradle.plugins.types.interfaces.options.config.INet

class Net(args: List<INet?>) : CargoPriorityResolveBase<INet>(args), INet {
    override val retry: Int?
        get() = resolve { it.retry }
    override val gitFetchWithCli: Boolean?
        get() = resolve { it.gitFetchWithCli }
    override val offline: Boolean?
        get() = resolve { it.offline }

    override val environmentPrefix: String
        get() = "NET"
    override val environmentPropertyMapper: Map<String, () -> Any?>
        get() = mapOf(
            "RETRY" to { retry },
            "GIT_FETCH_WITH_CLI" to { gitFetchWithCli },
            "OFFLINE" to { offline }
        )
}