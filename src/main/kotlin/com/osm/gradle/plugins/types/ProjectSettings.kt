package com.osm.gradle.plugins.types

import org.gradle.api.logging.LogLevel

open class ProjectSettings : RusticConfigurableBase() {
    open var projectLocation: String? = null
    open var captureStandardOutput: LogLevel = LogLevel.LIFECYCLE
    open var captureStandardError: LogLevel = LogLevel.ERROR

    open fun projectLocation(value: String?) {
        projectLocation = value
    }

    open fun captureStandardOutput(value: LogLevel) {
        captureStandardOutput = value
    }

    open fun captureStandardError(value: LogLevel) {
        captureStandardError = value
    }
}