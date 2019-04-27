package com.osm.gradle.plugins

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
        project.apply { it.plugin("rustic") }
    }
}