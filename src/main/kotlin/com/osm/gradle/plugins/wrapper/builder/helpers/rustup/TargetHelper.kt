package com.osm.gradle.plugins.wrapper.builder.helpers.rustup

import com.osm.gradle.plugins.types.interfaces.IConfigBase
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.helpers.BuilderHelper
import com.osm.gradle.plugins.wrapper.builder.options.rustup.TargetOptions

object TargetHelper {
    class AddHelper : BuilderHelper {
        override fun put(opt: IConfigBase?, builder: OptionBuilder) {
            opt?.apply {
                target?.also {
                    builder.put(TargetOptions.Add(it))
                }
            }
        }
    }

    class ListHelper : BuilderHelper {
        override fun put(opt: IConfigBase?, builder: OptionBuilder) {
            builder.put(TargetOptions.List())
        }
    }
}


