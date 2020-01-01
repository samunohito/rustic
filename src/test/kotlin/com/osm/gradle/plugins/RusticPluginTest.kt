package com.osm.gradle.plugins

import com.osm.gradle.plugins.wrapper.Rustup
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.options.rustup.TargetOptions
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
                    AR : "/usr/bin/i686-w64-mingw32-gcc-ar",
                    CC : "/usr/bin/i686-w64-mingw32-gcc"
            ]
        }

        "x86_64-pc-windows-gnu" {
            target "x86_64-pc-windows-gnu"
            environments = [
                    AR : "/usr/bin/x86_64-w64-mingw32-gcc-ar",
                    CC : "/usr/bin/x86_64-w64-mingw32-gcc"
            ]
        }

        "i686-apple-darwin" {
            enabled false
            target "i686-apple-darwin"
            environments = [
                    AR : "",
                    CC : ""
            ]
        }

        "x86_64-apple-darwin" {
            enabled false
            target "x86_64-apple-darwin"
            environments = [
                    AR : "",
                    CC : ""
            ]
        }

        "i686-unknown-linux-gnu" {
            target "i686-unknown-linux-gnu"
            environments = [
                    AR : "/usr/bin/i686-linux-gnu-gcc-ar-8",
                    CC : "/usr/bin/i686-linux-gnu-gcc-8"
            ]
        }

        "x86_64-unknown-linux-gnu" {
            target "x86_64-unknown-linux-gnu"
            environments = [
                    AR : "/usr/bin/x86_64-linux-gnu-gcc-ar-8",
                    CC : "/usr/bin/x86_64-linux-gnu-gcc-8"
            ]
        }

        "arm-unknown-linux-gnueabihf" {
            target "arm-unknown-linux-gnueabihf"
            environments = [
                    AR : "/usr/bin/arm-linux-gnueabihf-gcc-ar-8",
                    CC : "/usr/bin/arm-linux-gnueabihf-gcc-8"
            ]
        }

        "aarch64-unknown-linux-gnu" {
            target "aarch64-unknown-linux-gnu"
            environments = [
                    AR : "/usr/bin/aarch64-linux-gnu-gcc-ar-8",
                    CC : "/usr/bin/aarch64-linux-gnu-gcc-8"
            ]
        }

        "i686-linux-android" {
            target "i686-linux-android"
            environments = [
                    AR : "/usr/local/lib/android-ndk-r20b/toolchains/llvm/prebuilt/linux-x86_64/bin/i686-linux-android-ar",
                    CC : "/usr/local/lib/android-ndk-r20b/toolchains/llvm/prebuilt/linux-x86_64/bin/i686-linux-android26-clang"
            ]
        }

        "x86_64-linux-android" {
            target "x86_64-linux-android"
            environments = [
                    AR : "/usr/local/lib/android-ndk-r20b/toolchains/llvm/prebuilt/linux-x86_64/bin/x86_64-linux-android-ar",
                    CC : "/usr/local/lib/android-ndk-r20b/toolchains/llvm/prebuilt/linux-x86_64/bin/x86_64-linux-android26-clang"
            ]
        }

        "arm-linux-androideabi" {
            target "arm-linux-androideabi"
            environments = [
                    AR : "/usr/local/lib/android-ndk-r20b/toolchains/llvm/prebuilt/linux-x86_64/bin/arm-linux-androideabi-ar",
                    CC : "/usr/local/lib/android-ndk-r20b/toolchains/llvm/prebuilt/linux-x86_64/bin/armv7a-linux-androideabi26-clang"
            ]
        }

        "aarch64-linux-android" {
            target "aarch64-linux-android"
            environments = [
                    AR : "/usr/local/lib/android-ndk-r20b/toolchains/llvm/prebuilt/linux-x86_64/bin/aarch64-linux-android-ar",
                    CC : "/usr/local/lib/android-ndk-r20b/toolchains/llvm/prebuilt/linux-x86_64/bin/aarch64-linux-android26-clang"
            ]
        }
    }

    variants.all {
        String target = it.target
        String cc = it.environments["CC"]
        if (target != null && cc != null) {
            String linkerEnvName = "CARGO_TARGET_" + target.replace("-", "_").toUpperCase() + "_LINKER"
            it.environments.put(linkerEnvName, cc)
        }
        
        println(it.target)
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

    @Test
    fun cargoBuild() {
        val builder = OptionBuilder()
        builder.put(TargetOptions.List())

        val rustup = Rustup()
        rustup.target(builder)
    }
}
