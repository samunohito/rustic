package com.osm.gradle.plugins.types.config.options

import com.osm.gradle.plugins.types.config.options.option.Base
import com.osm.gradle.plugins.types.interfaces.options.ICleanOptions

class CleanOptions : Base(), ICleanOptions {
    override var doc: Boolean? = null
    override var release: Boolean? = null

    fun doc(value: Boolean?) {
        doc = value
    }

    fun release(value: Boolean?) {
        release = value
    }
}