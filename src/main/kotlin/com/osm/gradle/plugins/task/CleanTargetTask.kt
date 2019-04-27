package com.osm.gradle.plugins.task

import com.osm.gradle.plugins.Rustic
import com.osm.gradle.plugins.params.options.CleanOptions
import com.osm.gradle.plugins.wrapper.Cargo
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.helpers.CleanOptionsHelper

open class CleanTargetTask : RustTaskBase() {
    lateinit var options: CleanOptions

    override fun call(cargo: Cargo) {
        val builder = OptionBuilder()
        CleanOptionsHelper().put(options, builder)
        cargo.clean(builder)
    }

    companion object Factory {
        fun create(
            name: String,
            rustic: Rustic,
            options: CleanOptions
        ): CleanTargetTask {
            rustic.project.tasks.removeIf { it.name == name }
            val self = rustic.project.tasks.create(name, CleanTargetTask::class.java)
            self.rustic = rustic
            self.options = options
            return self
        }
    }
}
