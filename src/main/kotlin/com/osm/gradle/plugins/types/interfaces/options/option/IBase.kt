package com.osm.gradle.plugins.types.interfaces.options.option

import com.osm.gradle.plugins.types.interfaces.options.config.ICargoConfig

interface IBase {
    val color: String?
    val frozen: Boolean?
    val locked: Boolean?
    val manifestPath: String?
    val offline: Boolean?
    val packageName: Iterable<String?>?
    val quiet: Boolean?
    val target: String?
    val targetDir: String?
    val verbose: Boolean?

    val cargoConfig: ICargoConfig
}