package com.osm.gradle.plugins.wrapper

import com.osm.gradle.plugins.types.config.DefaultConfig
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.OptionHelper
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

class CargoTest {
    @Rule
    @JvmField
    val testProjectDir = TemporaryFolder()
    lateinit var manifest: File
    lateinit var cargo: Cargo

    @Before
    fun setUp() {
        testProjectDir.create()

        manifest = testProjectDir.newFile("Cargo.toml")
        manifest.writeText(
            """
            [package]
            name = "rustic_test"
            version = "0.1.0"
            
            [lib]
            name = "rustic_test"
            path = "src/lib.rs"
            """.trimIndent()
        )

        testProjectDir.newFolder("src")
        testProjectDir.newFile("src/lib.rs").writeText(
            """
            pub fn test() {
                println!("test")
            }
            """.trimIndent()
        )

        cargo = Cargo()
        cargo.workingDirectory = testProjectDir.root.toPath()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun build() {
        val config = DefaultConfig()
        config.buildOptions.targetDir = ""

        val b = OptionBuilder()
        OptionHelper.put(config, b)

        cargo.build(b)
    }

    @Test
    fun version() {
        cargo.version()
    }
}
