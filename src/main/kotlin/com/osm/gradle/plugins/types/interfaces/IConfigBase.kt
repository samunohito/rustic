package com.osm.gradle.plugins.types.interfaces

import com.osm.gradle.plugins.types.interfaces.options.*
import com.osm.gradle.plugins.types.interfaces.options.config.ICargoConfig

interface IConfigBase {
    val name: String
    val enabled: Boolean?
    val environments: Map<String?, String?>?

    val defaultOptions: IDefaultOptions
    val buildOptions: IBuildOptions
    val checkOptions: ICheckOptions
    val testOptions: ITestOptions
    val benchOptions: IBenchOptions
    val cleanOptions: ICleanOptions
}