package com.osm.gradle.plugins.task

import com.osm.gradle.plugins.process.IRusticTaskProcess
import com.osm.gradle.plugins.process.NothingTaskProcess
import com.osm.gradle.plugins.process.cargo.*
import com.osm.gradle.plugins.process.rustup.TargetAddTaskProcess
import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.config.DefaultConfig
import com.osm.gradle.plugins.types.interfaces.options.option.IBase
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.util.string.toCamelCase
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.internal.AbstractTask

class TaskGenerator(
    private val project: Project,
    private val settings: ProjectSettings,
    private val defaultConfig: DefaultConfig
) {
    private val nothingTaskProcess = NothingTaskProcess()

    fun createVariantTasks(variants: List<BuildVariant>) {
        if (variants.isEmpty()) {
            return
        }

        if (variants.any { it.flavor != null }) {
            createVariantTasksHasFlavor("rustBuild", variants) { BuildTaskProcess(project, settings, it) }
            createVariantTasksHasFlavor("rustCheck", variants) { CheckTaskProcess(project, settings, it) }
            createVariantTasksHasFlavor("rustTest", variants) { TestTaskProcess(project, settings, it) }

            val benchList = variants
                .map { BuildVariant(project, settings, it.default, null, it.flavor) }
                .distinctBy { it.name }
            createBenchTasksHasFlavor(benchList)
        } else {
            createVariantTasks("rustBuild", variants) { BuildTaskProcess(project, settings, it) }
            createVariantTasks("rustCheck", variants) { CheckTaskProcess(project, settings, it) }
            createVariantTasks("rustTest", variants) { TestTaskProcess(project, settings, it) }
            createBenchTasks()
        }
    }

    private fun createVariantTasks(
        category: String,
        variants: List<BuildVariant>,
        processGenerator: (BuildVariant) -> IRusticTaskProcess<out IBase>
    ) {
        val root = RusticTask.obtain(project.tasks, category, nothingTaskProcess)

        variants.forEach { variant ->
            val process = processGenerator(variant)
            val subTask = RusticTask.obtain(project.tasks, getTaskName(category, variant), process)
            root.dependsOn(subTask)

            createTargetAddTask(variant, process.options, subTask)
        }
    }

    private fun createVariantTasksHasFlavor(
        category: String,
        variants: List<BuildVariant>,
        processGenerator: (BuildVariant) -> IRusticTaskProcess<out IBase>
    ) {
        val root = RusticTask.obtain(project.tasks, category, nothingTaskProcess)

        val subTaskMap = variants.filter { it.flavor == null }.associateBy({ it.name }) {
            RusticTask.obtain(project.tasks, getTaskName(category, it), nothingTaskProcess)
        }

        subTaskMap.values.forEach {
            root.dependsOn(it)
        }

        variants.filter { it.flavor != null }.forEach { variant ->
            val process = processGenerator(variant)
            val subChildTask = RusticTask.obtain(project.tasks, getTaskName(category, variant), process)

            val subTask = subTaskMap[variant.parentName] ?: error("invalid subTask name.")
            subTask.dependsOn(subChildTask)

            createTargetAddTask(variant, process.options, subChildTask)
        }
    }

    private fun createBenchTasks() {
        val prefix = "rustBench"
        val variant = BuildVariant(project, settings, defaultConfig, null, null)
        val process = BenchTaskProcess(project, settings, variant)
        val root = RusticTask.obtain(project.tasks, prefix, process)
        createTargetAddTask(variant, process.options, root)
    }

    private fun createBenchTasksHasFlavor(variants: List<BuildVariant>) {
        val prefix = "rustBench"
        val root = RusticTask.obtain(project.tasks, prefix, nothingTaskProcess)

        variants.filter { it.flavor != null }.forEach { variant ->
            val process = BenchTaskProcess(project, settings, variant)
            val subTask = RusticTask.obtain(project.tasks, getTaskName(prefix, variant), process)
            root.dependsOn(subTask)

            createTargetAddTask(variant, process.options, subTask)
        }
    }

    private fun createTargetAddTask(variant: BuildVariant, options: IBase, bind: Task) {
        options.target?.also { target ->
            val prefix = "rustTargetAdd"

            val process = TargetAddTaskProcess(project, settings, variant, options)
            val name = prefix + target.toCamelCase('_').toCamelCase('-').capitalize()
            val task = RusticTask.obtain(project.tasks, name, process)
            bind.dependsOn(task)
        }
    }

    fun createCleanTasks() {
        val prefix = "rustClean"
        val process = CleanTaskProcess(project, settings, BuildVariant(project, settings))
        RusticTask.obtain(project.tasks, prefix, process)
    }

    private fun getParentTaskName(category: String, variant: BuildVariant): String {
        return (category + variant.parentName)
    }

    private fun getTaskName(category: String, variant: BuildVariant): String {
        return (category + variant.name)
    }
}