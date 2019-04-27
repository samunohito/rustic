package com.osm.gradle.plugins.util.process

import java.io.FileNotFoundException
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class ProcessingParameters private constructor(
    val executablePath: Path,
    val environment: Map<String, String>,
    val workingDirectory: Path?,
    val encoding: Charset,
    val arguments: List<String>,
    val printStdOut: Boolean,
    val printStdErr: Boolean,
    val isRedirectErrStream: Boolean
) {
    companion object {
        fun of(
            execPath: Path,
            envs: Map<String, String> = emptyMap(),
            workDir: Path? = null,
            enc: Charset = Charsets.UTF_8,
            args: List<String> = emptyList(),
            inheritSystemEnv: Boolean = true,
            printStdOut: Boolean = true,
            printStdErr: Boolean = true,
            isRedirectErrStream: Boolean = false
        ): ProcessingParameters {

            val map = mutableMapOf<String, String>()
            if (inheritSystemEnv) {
                map.putAll(System.getenv())
            }

            map.putAll(envs)

            return ProcessingParameters(
                execPath,
                envs,
                workDir,
                enc,
                args,
                printStdOut,
                printStdErr,
                isRedirectErrStream
            )
        }

        fun of(
            executableName: String,
            envs: Map<String, String> = emptyMap(),
            workDir: Path? = null,
            enc: Charset = Charsets.UTF_8,
            args: List<String> = emptyList(),
            inheritSystemEnv: Boolean = true,
            printStdOut: Boolean = true,
            printStdErr: Boolean = true,
            isRedirectErrStream: Boolean = false
        ): ProcessingParameters {

            if (Files.exists(Paths.get(executableName))) {
                return of(
                    Paths.get(executableName),
                    envs,
                    workDir,
                    enc,
                    args,
                    inheritSystemEnv,
                    printStdOut,
                    printStdErr,
                    isRedirectErrStream
                )
            }

            val separator = System.getProperty("path.separator")
            val executablePath = System.getenv("PATH")
                .split(separator)
                .map {
                    System.getenv("PATHEXT")
                        ?.split(separator)
                        ?.map { ext -> Paths.get(it, "$executableName$ext") }
                        ?: listOf(Paths.get(it, executableName))
                }
                .flatten()
                .find { Files.exists(it) }
                ?: throw FileNotFoundException("not found executable : $executableName")

            return of(
                executablePath,
                envs,
                workDir,
                enc,
                args,
                inheritSystemEnv,
                printStdOut,
                printStdErr,
                isRedirectErrStream
            )
        }
    }
}