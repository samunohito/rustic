package com.osm.gradle.plugins.wrapper

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
        cargo.build()
    }

    @Test
    fun version() {
        cargo.version()
    }
}
