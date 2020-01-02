package com.osm.gradle.plugins.process

/**
 * Class used when you don't want RusticTask to do anything.
 */
class NothingTaskProcess : IRusticTaskProcess {
    /**
     * Does nothing. This is a dummy process.
     */
    override fun run() {
        // NOP
    }
}