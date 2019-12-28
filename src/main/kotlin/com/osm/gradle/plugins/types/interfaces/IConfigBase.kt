package com.osm.gradle.plugins.types.interfaces

import com.osm.gradle.plugins.types.interfaces.options.*

interface IConfigBase {
    val name: String
    val enabled: Boolean?
    val targetDir: String?
    val environments: Map<String, String>
    val target: String?
    val features: Iterable<String>?
    val targetSelection: ISelection
    val buildOptions: IBuildOptions
    val checkOptions: ICheckOptions
    val testOptions: ITestOptions
    val benchOptions: IBenchOptions
}