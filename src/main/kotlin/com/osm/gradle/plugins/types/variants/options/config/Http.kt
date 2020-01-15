package com.osm.gradle.plugins.types.variants.options.config

import com.osm.gradle.plugins.types.interfaces.options.config.IHttp

class Http(args: List<IHttp?>) : CargoPriorityResolveBase<IHttp>(args), IHttp {
    override val proxy: String?
        get() = resolve { it.proxy }
    override val timeout: Int?
        get() = resolve { it.timeout }
    override val cainfo: String?
        get() = resolve { it.cainfo }
    override val checkRevoke: String?
        get() = resolve { it.checkRevoke }
    override val sslVersion: String?
        get() = resolve { it.sslVersion }
    override val lowSpeedLimit: Int?
        get() = resolve { it.lowSpeedLimit }
    override val multiplexing: Boolean?
        get() = resolve { it.multiplexing }
    override val debug: Boolean?
        get() = resolve { it.debug }

    override val environmentPrefix: String
        get() = "HTTP"
    override val environmentPropertyMapper: Map<String, () -> Any?>
        get() = mapOf(
            "PROXY" to { proxy },
            "TIMEOUT" to { timeout },
            "CAINFO" to { cainfo },
            "CHECK_REVOKE" to { checkRevoke },
            "SSL_VERSION" to { sslVersion },
            "LOW_SPEED_LIMIT" to { lowSpeedLimit },
            "MULTIPLEXING" to { multiplexing },
            "DEBUG" to { debug }
        )
}