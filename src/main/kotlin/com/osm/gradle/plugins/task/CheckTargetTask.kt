package com.osm.gradle.plugins.task

import com.osm.gradle.plugins.wrapper.Cargo
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.helpers.BuildOptionsHelper
import com.osm.gradle.plugins.wrapper.builder.helpers.OptionsHelper
import com.osm.gradle.plugins.wrapper.builder.helpers.SelectionHelper

open class CheckTargetTask : RustTaskBase() {
    override fun call(cargo: Cargo) {
        val builder = OptionBuilder()
        variant.getOptionsNonNull()
            .forEach { opt ->
                OptionsHelper().put(opt, builder)
                SelectionHelper().put(opt, builder)
                BuildOptionsHelper().put(opt, builder)
            }

        cargo.check(builder)
    }
}
