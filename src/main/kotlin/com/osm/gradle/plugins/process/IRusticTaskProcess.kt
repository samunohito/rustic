package com.osm.gradle.plugins.process

/**
 * This is the interface for writing the logic executed by RusticTask.
 */
interface IRusticTaskProcess {
    /**
     * This method is called when a RusticTask is executed.
     */
    fun run()
}