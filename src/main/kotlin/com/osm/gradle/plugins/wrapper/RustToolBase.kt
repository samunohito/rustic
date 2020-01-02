package com.osm.gradle.plugins.wrapper

import com.osm.gradle.plugins.util.iterable.IterableExtensions.concat
import com.osm.gradle.plugins.util.process.Processing
import com.osm.gradle.plugins.util.process.ProcessingFailedException
import com.osm.gradle.plugins.util.process.ProcessingParameters
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import java.nio.file.Path

/**
 * This class has the ability to call another application from this application.
 */
abstract class RustToolBase {
    /**
     * Set the path to the executable file. For relative paths, it is resolved based on [workingDirectory].
     * If only the file name is set, not the path, the location of the executable
     * is searched from the environment variable PATH.
     */
    abstract val executable: String
    /**
     * Set the path to use as the working directory when running the application.
     */
    var workingDirectory: Path? = null
    /**
     * Set the environment variables used when running the application.
     */
    val additionalEnvironment: MutableMap<String, String> = mutableMapOf()

    /**
     * Run external applications synchronously. Wait for the executed application to complete.
     *
     * @param args Arguments to pass to the external application. Options must be passed as items in a list, not separated by spaces.
     */
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

    /**
     * Run external applications synchronously. Wait for the executed application to complete.
     *
     * @param args Arguments to pass to the external application. Options must be passed as items in a list, not separated by spaces.
     */
    @Throws(ProcessingFailedException::class)
    protected fun run(vararg args: String): String = run(args.toList())

    /**
     * Run external applications synchronously. Wait for the executed application to complete.
     *
     * @param commands Arguments to pass to the external application. Options must be passed as items in a list, not separated by spaces.
     * @param options Sets the builder for creating options.
     */
    @Throws(ProcessingFailedException::class)
    protected fun run(commands: List<String>, options: OptionBuilder): String {
        return run(commands.concat(options.toList()).toList())
    }

    /**
     * Run external applications synchronously. Wait for the executed application to complete.
     *
     * @param command Arguments to pass to the external application. Options must be passed as items in a list, not separated by spaces.
     * @param options Sets the builder for creating options.
     */
    @Throws(ProcessingFailedException::class)
    protected fun run(command: String, options: OptionBuilder): String {
        return run(listOf(command).concat(options.toList()).toList())
    }
}