package com.osm.gradle.plugins.types.interfaces.options

interface ICleanOptions {
    val doc: Boolean?
    val release: Boolean?
    val targetDir: String?
    val triple: String?
}