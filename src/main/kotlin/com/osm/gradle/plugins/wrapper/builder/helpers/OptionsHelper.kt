package com.osm.gradle.plugins.wrapper.builder.helpers

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.interfaces.IConfigBase
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.options.ManifestOptions
import com.osm.gradle.plugins.wrapper.builder.options.MiscellaneousOptions
import com.osm.gradle.plugins.wrapper.builder.options.OutputOptions
import java.nio.file.Paths

class OptionsHelper : BuilderHelper {
    override fun put(opt: IConfigBase?, builder: OptionBuilder) {
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