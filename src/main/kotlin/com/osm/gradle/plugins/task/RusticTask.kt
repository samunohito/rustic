package com.osm.gradle.plugins.task

import com.osm.gradle.plugins.TASK_GROUP_NAME
import com.osm.gradle.plugins.process.IRusticTaskProcess
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskContainer

open class RusticTask : DefaultTask() {
    lateinit var process: IRusticTaskProcess

    @TaskAction
    fun run() {
        process.run()
    }

    companion object Factory {
        fun obtain(tasks: TaskContainer, name: String, process: IRusticTaskProcess): RusticTask {
            val task = find(tasks, name) ?: tasks.create(name, RusticTask::class.java)

            task.process = process
            task.group = TASK_GROUP_NAME
            task.enabled = true

            return task
        }

        fun find(tasks: TaskContainer, name: String): RusticTask? {
            val findResult = tasks.findByName(name)
            return if (findResult != null) {
                RusticTask::class.java.cast(findResult)
            } else {
                null
            }
        }

        fun disableAll(tasks: TaskContainer) {
            tasks.filter { it.group == TASK_GROUP_NAME }.forEach {
                it.enabled = false
            }
        }
    }
}