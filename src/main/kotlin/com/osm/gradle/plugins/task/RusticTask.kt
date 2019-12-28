package com.osm.gradle.plugins.task

import com.osm.gradle.plugins.TASK_GROUP_NAME
import com.osm.gradle.plugins.process.IRusticTaskProcess
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskContainer

open class RusticTask : DefaultTask() {
    lateinit var process: IRusticTaskProcess

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

//        private val cache = ConcurrentHashMap<String, RusticTask>()
//
//        fun obtain(tasks: TaskContainer, name: String, process: IRusticTaskProcess): RusticTask {
//            val task = if (cache[name] != null) {
//                cache[name]!!
//            } else {
//                tasks.create(name, RusticTask::class.java)
//            }
//
//            cache[name] = task
//
//            task.process = process
//            task.group = TASK_GROUP_NAME
//            task.enabled = true
//
//            return task
//        }
//
//        fun find(name: String): RusticTask? {
//            return cache[name]
//        }
//
//        fun disableAll() {
//            cache.values.forEach {
//                it.enabled = false
//            }
//        }
    }

    @TaskAction
    fun run() {
        process.run()
    }
}