package com.osm.gradle.plugins.task

import com.osm.gradle.plugins.process.IRusticTaskProcess
import com.osm.gradle.plugins.process.NothingTaskProcess
import com.osm.gradle.plugins.process.cargo.*
import com.osm.gradle.plugins.process.rustup.TargetAddTaskProcess
import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.config.options.CleanOptions
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.util.string.toCamelCase
import org.gradle.api.Project
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

class TaskGenerator(
    private val project: Project,
    private val settings: ProjectSettings
) {
    private val nothingTaskProcess = NothingTaskProcess()
    private val queue = LinkedBlockingQueue<RequestItem>()
    private val looper: Runnable

    init {
        looper = thread {
            while (true) {
                val item = queue.take()
                createTasksInternal(item.variants)
                item.callback()
            }
        }
    }

    fun createVariantTasksRequest(item: RequestItem) {
        queue.add(item)
    }

    private fun createTasksInternal(variants: List<BuildVariant>) {
        val targetAddTaskMap = createTargetAddTasks(variants)

        createVariantTasks("rustBuild", variants, targetAddTaskMap) { BuildTaskProcess(project, settings, it) }
        createVariantTasks("rustCheck", variants, targetAddTaskMap) { CheckTaskProcess(project, settings, it) }
        createVariantTasks("rustTest", variants, targetAddTaskMap) { TestTaskProcess(project, settings, it) }

        val benchList = variants
            .map { BuildVariant(project, settings, it.default, null, it.flavor) }
            .distinctBy { it.name }
        createVariantTasks("rustBench", benchList, targetAddTaskMap) { BenchTaskProcess(project, settings, it) }
    }

    private fun createTargetAddTasks(variants: List<BuildVariant>): Map<String, RusticTask> {
        val prefix = "rustTargetAdd"
        val root = RusticTask.obtain(project.tasks, prefix, nothingTaskProcess)

        return variants
            .distinctBy { it.target }
            .filter { it.target != null }
            .associateBy({ it.target!! }) {
                val name = prefix + it.target!!.toCamelCase('-').toCamelCase('_')
                val process = TargetAddTaskProcess(project, settings, it)
                val subTask = RusticTask.obtain(project.tasks, name, process)

                root.dependsOn(subTask)

                return@associateBy subTask
            }
    }

    private fun createVariantTasks(
        category: String,
        variants: List<BuildVariant>,
        targetAddTaskMap: Map<String, RusticTask>,
        processGenerator: (BuildVariant) -> IRusticTaskProcess
    ) {
        val root = RusticTask.obtain(project.tasks, category, nothingTaskProcess)

        variants.forEach { variant ->
            val targetAddTask = targetAddTaskMap[variant.target ?: ""]
            val process = processGenerator(variant)
            if (variant.flavor == null) {
                val subTask = RusticTask.obtain(project.tasks, getTaskName(category, variant), process)
                root.dependsOn(subTask)
                targetAddTask?.also { subTask.dependsOn(it) }
            } else {
                val subTask = RusticTask.obtain(project.tasks, getParentTaskName(category, variant), nothingTaskProcess)
                val subChildTask = RusticTask.obtain(project.tasks, getTaskName(category, variant), process)
                subTask.dependsOn(subChildTask)
                root.dependsOn(subTask)
                targetAddTask?.also { subChildTask.dependsOn(it) }
            }
        }
    }

    fun createCleanTasks() {
        val prefix = "rustClean"

        val process = CleanTaskProcess(
            project,
            settings,
            BuildVariant(project, settings),
            CleanOptions()
        )
        RusticTask.obtain(project.tasks, prefix, process)
//
//        val docOptions = CleanOptions()
//        docOptions.doc = true
//        CleanTargetTask.create("${prefix}Doc", this, CleanOptions())
//
//        val releaseOptions = CleanOptions()
//        releaseOptions.release = true
//        CleanTargetTask.create("${prefix}Release", this, CleanOptions())
//
//        val cleanTriple = TaskUtility.generate(config.tasks,("${prefix}Triple"))
//        variants.mapNotNull { it.config }.forEach {
//            val opt = CleanOptions()
//            opt.triple = it.triple
//            cleanTriple.dependsOn(
//                CleanTargetTask.create(
//                    "${cleanTriple.name}${it.name.capitalize()}",
//                    this@Rustic,
//                    opt
//                )
//            )
//        }
    }

    private fun getParentTaskName(category: String, variant: BuildVariant) =
        (category + variant.parentName)

    private fun getTaskName(category: String, variant: BuildVariant) = (category + variant.name)

    class RequestItem(val variants: List<BuildVariant>, val callback: () -> Unit)
}