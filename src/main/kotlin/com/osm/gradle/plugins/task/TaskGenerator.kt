package com.osm.gradle.plugins.task

import com.osm.gradle.plugins.process.IRusticTaskProcess
import com.osm.gradle.plugins.process.NothingTaskProcess
import com.osm.gradle.plugins.process.cargo.*
import com.osm.gradle.plugins.process.rustup.TargetAddTaskProcess
import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.interfaces.options.option.IBase
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.util.string.toCamelCase
import org.gradle.api.Project
import org.gradle.api.Task

class TaskGenerator(
    private val project: Project,
    private val settings: ProjectSettings
) {
    private val nothingTaskProcess = NothingTaskProcess()

    fun createVariantTasks(variants: List<BuildVariant>) {
        if (variants.any { it.flavor != null }) {
            createVariantTasksHasFlavor("rustBuild", variants) { BuildTaskProcess(project, settings, it) }
            createVariantTasksHasFlavor("rustCheck", variants) { CheckTaskProcess(project, settings, it) }
            createVariantTasksHasFlavor("rustTest", variants) { TestTaskProcess(project, settings, it) }
        } else {
            createVariantTasks("rustBuild", variants) { BuildTaskProcess(project, settings, it) }
            createVariantTasks("rustCheck", variants) { CheckTaskProcess(project, settings, it) }
            createVariantTasks("rustTest", variants) { TestTaskProcess(project, settings, it) }
        }

        val benchList = variants
            .map { BuildVariant(project, settings, it.default, null, it.flavor) }
            .distinctBy { it.name }
        createVariantTasks("rustBench", benchList) { BenchTaskProcess(project, settings, it) }
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
        variants.forEach { variant ->
            val process = processGenerator(variant)
            val subTask = RusticTask.obtain(project.tasks, getParentTaskName(category, variant), nothingTaskProcess)
            val subChildTask = RusticTask.obtain(project.tasks, getTaskName(category, variant), process)
            subTask.dependsOn(subChildTask)
            root.dependsOn(subTask)

            createTargetAddTask(variant, process.options, subChildTask)
        }
    }

    private fun createTargetAddTask(variant: BuildVariant, options: IBase, bind: Task) {
        options.target?.also { target ->
            val prefix = "rustTargetAdd"

            val process = TargetAddTaskProcess(project, settings, variant, options)
            val name = prefix + target.toCamelCase('_').toCamelCase('-').capitalize()
            val task = RusticTask.obtain(project.tasks, name, process)
            bind.dependsOn(task)

            val root = RusticTask.obtain(project.tasks, prefix, nothingTaskProcess)
            root.dependsOn(task)
        }
    }

    fun createCleanTasks() {
        val prefix = "rustClean"
        val process = CleanTaskProcess(project, settings, BuildVariant(project, settings))
        RusticTask.obtain(project.tasks, prefix, process)
    }

    private fun getParentTaskName(category: String, variant: BuildVariant) =
        (category + variant.parentName)

    private fun getTaskName(category: String, variant: BuildVariant) = (category + variant.name)
}