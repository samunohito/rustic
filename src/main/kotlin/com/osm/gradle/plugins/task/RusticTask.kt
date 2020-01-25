package com.osm.gradle.plugins.task

import com.osm.gradle.plugins.TASK_GROUP_NAME
import com.osm.gradle.plugins.process.IRusticTaskProcess
import org.gradle.api.DefaultTask
import org.gradle.api.Task
import org.gradle.api.internal.AbstractTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskContainer

open class RusticTask : DefaultTask() {
    private lateinit var process: IRusticTaskProcess<*>

    @TaskAction
    fun run() {
        process.run()
    }

    companion object Factory {
        fun obtain(tasks: TaskContainer, name: String, process: IRusticTaskProcess<*>): Task {
            val task = tasks.findByName(name) ?: tasks.create(name, RusticTask::class.java)

            task.group = TASK_GROUP_NAME
            task.enabled = true

            if (task is RusticTask) {
                task.process = process
            }

            if (task is AbstractTask) {
                task.taskActions.clear()
            }

            return task
        }

        fun disableAll(tasks: TaskContainer) {
            tasks.filter { it.group == TASK_GROUP_NAME }.forEach {
                it.enabled = false
            }
        }
    }
}