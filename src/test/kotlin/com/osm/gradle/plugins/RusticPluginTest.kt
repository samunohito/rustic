package com.osm.gradle.plugins

import org.gradle.testfixtures.ProjectBuilder
import org.gradle.testkit.runner.GradleRunner
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class RusticPluginTest {
    @Rule
    @JvmField
    val testProjectDir = TemporaryFolder()
    val srcJarPath = Paths.get("build/libs/rustic-0.1.0.jar")
    lateinit var dstJarPath: Path
    var buildFile: File? = null

    @Before
    fun setUp() {
        buildFile = testProjectDir.newFile("build.gradle")
        dstJarPath = Paths.get(testProjectDir.root.toPath().toString(), srcJarPath.fileName?.toString())
        Files.copy(srcJarPath, dstJarPath)
    }

    @After
    fun tearDown() {
    }

      //  @Test
    fun apply() {
        val project = ProjectBuilder.builder().build()
        project.apply {
            it.plugin("com.osm.gradle.plugins.rustic")
        }
    }

    @Test
    fun applyBlock() {
        buildFile?.writeText(
            """
buildscript {
    dependencies {
        classpath files('${dstJarPath}')
    }
}

apply plugin: 'com.osm.gradle.plugins.rustic'

rustic {
    projectSettings {
        projectLocation "${testProjectDir.root}"
    }
  
    defaultConfig {
        enabled = true
    }
     
    buildTypes {
        debug {
            target = "x86_64-unknown-linux-gnu"
            buildOptions {
                debug = true
            }
        }
        
        release {
            target = "x86_64-unknown-linux-gnu"
            buildOptions {
                debug = false
            }
        }
    }
    
    flavors {
        "i686-pc-windows-gnu" {
            target "i686-pc-windows-gnu"
            environments = [
                    AR    : "/usr/bin/i686-w64-mingw32-ar",
                    AS    : "/usr/bin/i686-w64-mingw32-as",
                    CC    : "/usr/bin/i686-w64-mingw32-gcc",
                    CXX   : "/usr/bin/i686-w64-mingw32-g++",
                    LD    : "/usr/bin/i686-w64-mingw32-ld",
                    RANLIB: "/usr/bin/i686-w64-mingw32-ranlib",
                    STRIP : "/usr/bin/i686-w64-mingw32-strip"
            ]
        }
 
        "x86_64-pc-windows-gnu" {
            enabled = false
            target "x86_64-pc-windows-gnu"
            environments = [
                    AR    : "/usr/bin/x86_64-w64-mingw32-ar",
                    AS    : "/usr/bin/x86_64-w64-mingw32-as",
                    CC    : "/usr/bin/x86_64-w64-mingw32-gcc",
                    CXX   : "/usr/bin/x86_64-w64-mingw32-g++",
                    LD    : "/usr/bin/x86_64-w64-mingw32-ld",
                    RANLIB: "/usr/bin/x86_64-w64-mingw32-ranlib",
                    STRIP : "/usr/bin/x86_64-w64-mingw32-strip"
            ]
            ext {
                hogeeeeee = "pipipipi"
            }
        }
        
        variants.all {
            if (it.flavor.hasProperty("hogeeeeee")) {
                println(it.flavor.hogeeeeee)
            }
        }
    }
}
        """
        )

        val result = GradleRunner.create()
            .withProjectDir(testProjectDir.root)
            .build()

        println(result.output)
        println(result.tasks)
    }

//    @Test
//    fun cargoBuild() {
//        val builder = OptionBuilder()
//        builder.put(CompilationOptions.Target("x86_64-unknown-linux-gnu"))
//
//        val cargo = Cargo()
//        cargo.build(builder)
//    }
}