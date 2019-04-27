package com.osm.gradle.plugins.wrapper

import com.osm.gradle.plugins.util.IterableExtensions.concat
import com.osm.gradle.plugins.util.process.Processing
import com.osm.gradle.plugins.util.process.ProcessingFailedException
import com.osm.gradle.plugins.util.process.ProcessingParameters
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import java.nio.file.Path

abstract class Base {
    abstract val executable: String

    var workingDirectory: Path? = null
    val additionalEnvironment: MutableMap<String, String> = mutableMapOf()

    @Throws(ProcessingFailedException::class)
    protected fun run(args: List<String>): String {
        val proc = Processing(
            ProcessingParameters.of(
                executable,
                workDir = workingDirectory,
                args = args,
                envs = additionalEnvironment
            )
        )
        proc.executeWaitFor()
        return proc.getStdOutText()
    }

    @Throws(ProcessingFailedException::class)
    protected fun run(vararg args: String): String = run(args.toList())

    @Throws(ProcessingFailedException::class)
    protected fun run(commands: List<String>, options: OptionBuilder): String {
        return run(commands.concat(options.toList()).toList())
    }

    @Throws(ProcessingFailedException::class)
    protected fun run(command: String, options: OptionBuilder): String {
        return run(listOf(command).concat(options.toList()).toList())
    }
}