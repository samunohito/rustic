package com.osm.gradle.plugins

import com.osm.gradle.plugins.wrapper.Cargo
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.options.CompilationOptions
import org.gradle.testfixtures.ProjectBuilder
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class RusticPluginTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun apply() {
        val project = ProjectBuilder.builder().build()
        project.apply {
            it.plugin("com.osm.gradle.plugins.rustic")
        }
    }

    @Test
    fun cargoBuild() {
        val builder = OptionBuilder()
        builder.put(CompilationOptions.Target("x86_64-unknown-linux-gnu"))

        val cargo = Cargo()
        cargo.build(builder)
    }
}