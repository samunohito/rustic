package com.osm.gradle.plugins.process

import com.osm.gradle.plugins.Rustic
import com.osm.gradle.plugins.params.BuildVariant
import com.osm.gradle.plugins.wrapper.Cargo
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.helpers.BenchOptionsHelper
import com.osm.gradle.plugins.wrapper.builder.helpers.OptionsHelper
import com.osm.gradle.plugins.wrapper.builder.helpers.SelectionHelper

open class BenchTargetTaskProcess(rustic: Rustic, variant: BuildVariant) : CargoTaskProcessBase(rustic, variant) {
    override fun call(cargo: Cargo) {
        val builder = OptionBuilder()
        variant.getOptionsNonNull()
            .forEach { opt ->
                OptionsHelper().put(opt, builder)
                SelectionHelper().put(opt, builder)
                BenchOptionsHelper().put(opt, builder)
            }

        cargo.bench(builder)
    }

}
