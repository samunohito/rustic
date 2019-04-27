package com.osm.gradle.plugins.wrapper.builder.helpers

import com.osm.gradle.plugins.params.OptionsBase
import com.osm.gradle.plugins.params.ProjectBuildOptions
import com.osm.gradle.plugins.params.ProjectFlavorOptions
import com.osm.gradle.plugins.params.ProjectOptions
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.options.*
import java.nio.file.Paths

class OptionsHelper : BuilderHelper {
    override fun put(opt: OptionsBase?, builder: OptionBuilder) {
        opt?.apply {
            targetDir?.also { builder.put(OutputOptions.TargetDir(Paths.get(it))) }
        }
    }

    fun put(opt: ProjectOptions?, builder: OptionBuilder) {
        opt?.apply {
            put(opt as OptionsBase, builder)
            targetDir?.also { builder.put(OutputOptions.TargetDir(Paths.get(it))) }
            jobs?.also { builder.put(MiscellaneousOptions.Jobs(it)) }
            manifestPath?.also { builder.put(ManifestOptions.ManifestPath(Paths.get(it))) }
        }
    }

    fun put(opt: ProjectBuildOptions?, builder: OptionBuilder) {
        opt?.apply {
            put(opt as OptionsBase, builder)
            targetDir?.also { builder.put(OutputOptions.TargetDir(Paths.get(it))) }
        }
    }

    fun put(opt: ProjectFlavorOptions?, builder: OptionBuilder) {
        opt?.apply {
            put(opt as OptionsBase, builder)
            triple?.also { builder.put(CompilationOptions.Target(it)) }
            targetDir?.also { builder.put(OutputOptions.TargetDir(Paths.get(it))) }
            features?.also { builder.put(FeatureSelection.Features(it.toList())) }
        }
    }
}