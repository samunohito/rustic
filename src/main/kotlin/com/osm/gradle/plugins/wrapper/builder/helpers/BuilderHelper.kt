package com.osm.gradle.plugins.wrapper.builder.helpers

import com.osm.gradle.plugins.types.config.ConfigBase
import com.osm.gradle.plugins.types.interfaces.IConfigBase
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder

interface BuilderHelper {
    fun put(opt: IConfigBase?, builder: OptionBuilder)
}