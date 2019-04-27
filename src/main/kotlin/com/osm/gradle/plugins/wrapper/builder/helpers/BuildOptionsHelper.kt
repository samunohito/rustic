package com.osm.gradle.plugins.wrapper.builder.helpers

import com.osm.gradle.plugins.params.OptionsBase
import com.osm.gradle.plugins.params.options.BuildOptions
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.options.CompilationOptions
import com.osm.gradle.plugins.wrapper.builder.options.OutputOptions
import java.nio.file.Paths

class BuildOptionsHelper : BuilderHelper {
    override fun put(opt: OptionsBase?, builder: OptionBuilder) {
        opt?.buildOptions?.apply {
            debug?.also {
                if (!it) {
                    builder.put(CompilationOptions.Release())
                } else {
                    builder.removeIfMatchType(CompilationOptions.Release::class.java)
                }
            }
            outputDirectory?.also { dir ->
                builder.put(OutputOptions.OutDir(Paths.get(dir)))
            }
        }
    }
}