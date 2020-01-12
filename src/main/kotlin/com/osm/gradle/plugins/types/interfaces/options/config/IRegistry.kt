package com.osm.gradle.plugins.types.interfaces.options.config

interface IRegistry {
    /**
     * URL of the registry index (defaults to the index of crates.io)
     */
    val index: String?
    /**
     * Name of the default registry to use (can be overridden with --registry)
     */
    val default: String?
}