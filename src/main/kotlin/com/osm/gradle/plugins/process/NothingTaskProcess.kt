package com.osm.gradle.plugins.process

import com.osm.gradle.plugins.types.interfaces.options.option.IBase
import org.gradle.internal.impldep.org.eclipse.jgit.errors.NotSupportedException

/**
 * Class used when you don't want RusticTask to do anything.
 */
class NothingTaskProcess : IRusticTaskProcess<IBase> {
    /**
     * Does nothing. This is a dummy process.
     */
    override fun run() {
        // NOP
    }

    override val options: IBase
        get() {
            throw NotSupportedException("options is not support.")
        }
}