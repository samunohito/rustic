package com.osm.gradle.plugins.util.process

import com.osm.gradle.plugins.log.LoggerSupport
import java.io.IOException
import javax.annotation.Nonnull
import kotlin.concurrent.thread

class Processing constructor(@Nonnull val param: ProcessingParameters) : LoggerSupport {
    private val outBuffer = AppendableBuffer(if (param.printStdOut) listOf(System.out) else emptyList())
    private val errBuffer = AppendableBuffer(if (param.printStdErr) listOf(System.err) else emptyList())

    @Throws(IOException::class)
    private fun processStartNew(): Process {
        val command = param.arguments.filter { it.isNotEmpty() }.toMutableList()
        command.add(0, param.executablePath.toAbsolutePath().toString())

        debug("running command....")
        debug(command.joinToString(" "))

        val processBuilder = ProcessBuilder(command)
        processBuilder.redirectErrorStream(param.isRedirectErrStream)
        processBuilder.directory(param.workingDirectory?.toFile())
        processBuilder.environment().putAll(param.environment)

        return processBuilder.start()
    }

    @Throws(ProcessingFailedException::class)
    fun executeConsume(): Triple<Thread, Thread, Process> {
        return try {
            val proc = processStartNew()

            val outThread = thread {
                proc.inputStream
                    .bufferedReader(param.encoding)
                    .forEachLine { outBuffer.append(it).append(System.lineSeparator()) }
            }

            val errThread = thread {
                proc.errorStream
                    .bufferedReader(param.encoding)
                    .forEachLine { errBuffer.append(it).append(System.lineSeparator()) }
            }

            Triple(outThread, errThread, proc)
        } catch (ex: Throwable) {
            throw ProcessingFailedException(ex)
        }
    }

    @Throws(ProcessingFailedException::class)
    fun executeWaitFor() {
        val ret = try {
            val (outThread, errThread, proc) = executeConsume()

            outThread.join()
            errThread.join()

            proc.waitFor()
        } catch (ex: Throwable) {
            throw ProcessingFailedException(ex)
        }

        if (ret != 0) {
            throw ProcessingFailedException(errBuffer.toString())
        }
    }

    fun getStdOutText() = outBuffer.toString()

    fun getStdErrText() = errBuffer.toString()
}