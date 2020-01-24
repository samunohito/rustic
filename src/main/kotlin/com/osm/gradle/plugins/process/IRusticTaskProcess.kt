package com.osm.gradle.plugins.process

import com.osm.gradle.plugins.types.interfaces.options.option.IBase

/**
 * This is the interface for writing the logic executed by RusticTask.
 */
interface IRusticTaskProcess<T : IBase> {
    val options: T

    /**
     * This method is called when a RusticTask is executed.
     */
    fun run()
}