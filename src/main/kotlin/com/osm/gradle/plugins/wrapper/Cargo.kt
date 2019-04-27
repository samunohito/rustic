package com.osm.gradle.plugins.wrapper

import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import java.nio.file.Path
import java.nio.file.Paths

class Cargo : Base() {
    override val executable: String = "cargo"

    fun build(options: OptionBuilder = OptionBuilder()): String {
        return run("build", options)
    }

    fun check(options: OptionBuilder = OptionBuilder()): String {
        return run("check", options)
    }

    fun new(path: Path, options: OptionBuilder = OptionBuilder()): String {
        return run(listOf("new", path.toAbsolutePath().toString()), options)
    }

    fun init(path: Path = workingDirectory ?: Paths.get(""), options: OptionBuilder = OptionBuilder()): String {
        return run(listOf("init", path.toAbsolutePath().toString()), options)
    }

    fun clean(options: OptionBuilder = OptionBuilder()): String {
        return run("clean", options)
    }

    fun doc(options: OptionBuilder = OptionBuilder()): String {
        return run("doc", options)
    }

    fun run(options: OptionBuilder = OptionBuilder()): String {
        return run("run", options)
    }

    fun test(options: OptionBuilder = OptionBuilder()): String {
        return run("test", options)
    }

    fun bench(options: OptionBuilder = OptionBuilder()): String {
        return run("bench", options)
    }

    fun version(): String {
        return run("--version")
    }
}