package com.osm.gradle.plugins.wrapper.builder.helpers.cargo

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.interfaces.IConfigBase
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.helpers.BuilderHelper
import com.osm.gradle.plugins.wrapper.builder.options.cargo.ManifestOptions
import com.osm.gradle.plugins.wrapper.builder.options.cargo.MiscellaneousOptions
import com.osm.gradle.plugins.wrapper.builder.options.cargo.OutputOptions
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