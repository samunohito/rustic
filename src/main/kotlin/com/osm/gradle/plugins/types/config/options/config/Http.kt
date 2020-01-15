package com.osm.gradle.plugins.types.config.options.config

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.config.IHttp

open class Http : RusticConfigurableBase(), IHttp {
    override var proxy: String? = null
    override var timeout: Int? = null
    override var cainfo: String? = null
    override var checkRevoke: String? = null
    override var sslVersion: String? = null
    override var lowSpeedLimit: Int? = null
    override var multiplexing: Boolean? = null
    override var debug: Boolean? = null

    open fun proxy(value: String?) {
        proxy = value
    }

    open fun timeout(value: Int?) {
        timeout = value
    }

    open fun cainfo(value: String?) {
        cainfo = value
    }

    open fun checkRevoke(value: String?) {
        checkRevoke = value
    }

    open fun sslVersion(value: String?) {
        sslVersion = value
    }

    open fun lowSpeedLimit(value: Int?) {
        lowSpeedLimit = value
    }

    open fun multiplexint(value: Boolean?) {
        multiplexing = value
    }

    open fun debug(value: Boolean?) {
        debug = value
    }
}