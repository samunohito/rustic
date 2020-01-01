package com.osm.gradle.plugins.process.cargo

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.wrapper.Cargo
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.helpers.cargo.BenchOptionsHelper
import com.osm.gradle.plugins.wrapper.builder.helpers.cargo.OptionsHelper
import com.osm.gradle.plugins.wrapper.builder.helpers.cargo.SelectionHelper
import org.gradle.api.Project

open class BenchTaskProcess(
    project: Project,
    settings: ProjectSettings,
    variant: BuildVariant
) : CargoTaskProcessBase(project, settings, variant) {
    override fun call(tool: Cargo) {
        val builder = OptionBuilder()

        OptionsHelper().put(variant, builder)
        OptionsHelper().put(settings, builder)
        SelectionHelper().put(variant, builder)
        BenchOptionsHelper()
            .put(variant, builder)

        tool.bench(builder)
    }

}
