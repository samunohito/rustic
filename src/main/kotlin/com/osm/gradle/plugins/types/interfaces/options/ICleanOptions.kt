package com.osm.gradle.plugins.types.interfaces.options

interface ICleanOptions {
    val color :String?
    val doc:Boolean?
    val frozen:Boolean?
    val help:Boolean?
    val locked:Boolean?
    val manifestPath :String?
    val offline:Boolean?
    val packageName: Iterable<String?>?
    val quiet:Boolean?
    val release:Boolean?
    val target :String?
    val targetDir :String?
    val verbose:Boolean?
}