package com.osm.gradle.plugins.process

import com.osm.gradle.plugins.Rustic
import com.osm.gradle.plugins.params.BuildVariant
import com.osm.gradle.plugins.params.options.CleanOptions
import com.osm.gradle.plugins.wrapper.Cargo
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.helpers.CleanOptionsHelper

open class CleanTargetTaskProcess(rustic: Rustic, variant: BuildVariant, val options: CleanOptions) :
    CargoTaskProcessBase(rustic, variant) {
    override fun call(cargo: Cargo) {
        val builder = OptionBuilder()
        CleanOptionsHelper().put(options, builder)
        cargo.clean(builder)
    }
}
