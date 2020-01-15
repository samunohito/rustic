package com.osm.gradle.plugins.types.config.options.config

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.config.IBuild

open class Build : RusticConfigurableBase(), IBuild {
    override var jobs: Int? = null
    override var rustc: String? = null
    override var rustcWrapper: String? = null
    override var rustDoc: String? = null
    override var target: String? = null
    override var targetDir: String? = null
    override var rustFlags: Iterable<String?>? = null
    override var rustDocFlags: Iterable<String?>? = null
    override var incremental: Boolean? = null
    override var depInfoBaseDir: String? = null

    open fun jobs(value: Int?) {
        jobs = value
    }

    open fun rustc(value: String?) {
        rustc = value
    }

    open fun rustcWrapper(value: String?) {
        rustcWrapper = value
    }

    open fun rustDoc(value: String?) {
        rustDoc = value
    }

    open fun target(value: String?) {
        target = value
    }

    open fun targetDir(value: String?) {
        targetDir = value
    }

    open fun rustFlags(value: Iterable<String?>?) {
        rustFlags = value
    }

    open fun rustDocFlags(value: Iterable<String?>?) {
        rustDocFlags = value
    }

    open fun incremental(value: Boolean?) {
        incremental = value
    }

    open fun depInfoBaseDir(value: String?) {
        depInfoBaseDir = value
    }
}