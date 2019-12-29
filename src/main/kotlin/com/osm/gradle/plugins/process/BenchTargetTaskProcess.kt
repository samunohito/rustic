package com.osm.gradle.plugins.process

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.wrapper.Cargo
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.helpers.BenchOptionsHelper
import com.osm.gradle.plugins.wrapper.builder.helpers.OptionsHelper
import com.osm.gradle.plugins.wrapper.builder.helpers.SelectionHelper
import org.gradle.api.Project

open class BenchTargetTaskProcess(
    project: Project,
    settings: ProjectSettings,
    variant: BuildVariant
) : CargoTaskProcessBase(project, settings, variant) {
    override fun call(cargo: Cargo) {
        val builder = OptionBuilder()

        OptionsHelper().put(variant, builder)
        OptionsHelper().put(settings, builder)
        SelectionHelper().put(variant, builder)
        BenchOptionsHelper().put(variant, builder)

        cargo.bench(builder)
    }

}
