package com.osm.gradle.plugins.process.rustup

import com.osm.gradle.plugins.process.RusticTaskProcessBase
import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.interfaces.options.option.IBase
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.wrapper.Rustup
import org.gradle.api.Project

abstract class RustupTaskProcessBase(
    project: Project,
    settings: ProjectSettings,
    variant: BuildVariant
) : RusticTaskProcessBase<Rustup, IBase>(project, settings, variant) {
    override fun createToolBase() = Rustup()
}