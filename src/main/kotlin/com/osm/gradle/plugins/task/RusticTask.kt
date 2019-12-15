package com.osm.gradle.plugins.task

import com.osm.gradle.plugins.TASK_GROUP_NAME
import com.osm.gradle.plugins.process.RusticTaskProcessBase
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskContainer

open class RusticTask : DefaultTask() {
    lateinit var processBase : RusticTaskProcessBase

    companion object Factory {
        fun obtain(tasks: TaskContainer, name: String, processBase: RusticTaskProcessBase): RusticTask {
            val findResult = tasks.findByName(name)
            val targetTask = if (findResult == null) {
                tasks.create(name, RusticTask::class.java)
            } else {
                RusticTask::class.java.cast(findResult)
            }

            targetTask.processBase = processBase
            targetTask.group = TASK_GROUP_NAME

            return targetTask
        }
    }

    @TaskAction
    fun run() {
        processBase.run()
    }
}