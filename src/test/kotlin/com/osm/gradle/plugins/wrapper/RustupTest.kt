package com.osm.gradle.plugins.wrapper

import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.options.rustup.TargetOptions
import org.junit.After
import org.junit.Before
import org.junit.Test

class RustupTest {
    lateinit var rustup: Rustup

    @Before
    fun setUp() {
        rustup = Rustup()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun targetList() {
        val builder = OptionBuilder()
        builder.put(TargetOptions.List())

        rustup.target(builder)
    }
}
