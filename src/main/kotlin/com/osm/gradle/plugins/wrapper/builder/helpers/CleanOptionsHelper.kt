package com.osm.gradle.plugins.wrapper.builder.helpers

import com.osm.gradle.plugins.types.interfaces.options.ICleanOptions
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.options.CleanOptions

class CleanOptionsHelper {
    fun put(opt: ICleanOptions?, builder: OptionBuilder) {
        opt?.apply {
            doc?.also {
                if (it) {
                    builder.put(CleanOptions.Doc())
                } else {
                    builder.removeIfMatchType(CleanOptions.Doc::class.java)
                }
            }
            release?.also {
                if (it) {
                    builder.put(CleanOptions.Release())
                } else {
                    builder.removeIfMatchType(CleanOptions.Release::class.java)
                }
            }
            targetDir.also {
                if (it != null) {
                    builder.put(CleanOptions.TargetDir())
                } else {
                    builder.removeIfMatchType(CleanOptions.TargetDir::class.java)
                }
            }
            triple.also {
                if (it != null) {
                    builder.put(CleanOptions.Target())
                } else {
                    builder.removeIfMatchType(CleanOptions.Target::class.java)
                }
            }
        }
    }
}