package com.osm.gradle.plugins.types.interfaces.options.config

interface ITerm {
    /**
     * whether cargo provides verbose output
     */
    val verbose: Boolean?
    /**
     * whether cargo colorizes output
     */
    val color: String?
}