package com.osm.gradle.plugins.process

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.wrapper.Cargo
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.helpers.OptionsHelper
import com.osm.gradle.plugins.wrapper.builder.helpers.SelectionHelper
import com.osm.gradle.plugins.wrapper.builder.helpers.TestOptionsHelper
import org.gradle.api.Project
import java.nio.file.Path

open class TestTargetTaskProcess(
    project: Project,
    settings: ProjectSettings,
    variant: BuildVariant
) : CargoTaskProcessBase(project, settings, variant) {
    override fun call(cargo: Cargo) {
        val builder = OptionBuilder()

        OptionsHelper().put(variant, builder)
        SelectionHelper().put(variant, builder)
        TestOptionsHelper().put(variant, builder)

        cargo.test(builder)
    }
}
