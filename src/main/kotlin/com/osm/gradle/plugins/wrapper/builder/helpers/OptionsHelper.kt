package com.osm.gradle.plugins.wrapper.builder.helpers

import com.osm.gradle.plugins.params.ProjectSettings
import com.osm.gradle.plugins.params.project.OptionsBase
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.options.*
import java.nio.file.Paths

class OptionsHelper : BuilderHelper {
    override fun put(opt: OptionsBase?, builder: OptionBuilder) {
        opt?.apply {
            targetDir?.also { builder.put(OutputOptions.TargetDir(Paths.get(it))) }
        }
    }

    fun put(opt: ProjectSettings?, builder: OptionBuilder) {
        opt?.apply {
            jobs?.also { builder.put(MiscellaneousOptions.Jobs(it)) }
            manifestPath?.also { builder.put(ManifestOptions.ManifestPath(Paths.get(it))) }
        }
    }
}