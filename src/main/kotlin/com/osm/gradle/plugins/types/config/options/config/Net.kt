package com.osm.gradle.plugins.types.config.options.config

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.config.INet

open class Net : RusticConfigurableBase(), INet {
    override var retry: Int? = null
    override var gitFetchWithCli: Boolean? = null
    override var offline: Boolean? = null

    open fun retry(value: Int?) {
        retry = value
    }

    open fun gitFetchWithCli(value: Boolean?) {
        gitFetchWithCli = value
    }

    open fun offline(value: Boolean?) {
        offline = value
    }
}