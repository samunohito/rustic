package com.osm.gradle.plugins.task

import com.osm.gradle.plugins.process.IRusticTaskProcess
import com.osm.gradle.plugins.process.NothingTaskProcess
import com.osm.gradle.plugins.process.cargo.*
import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.config.options.CleanOptions
import com.osm.gradle.plugins.types.variants.BuildVariant
import org.gradle.api.Project

class TaskGenerator(
    private val project: Project,
    private val settings: ProjectSettings
) {
    private val nothingTaskProcess = NothingTaskProcess()

    fun createVariantTasks(item: RequestItem) {
        val variants = item.variants

        createVariantTasks("rustBuild", variants) { BuildTaskProcess(project, settings, it) }
        createVariantTasks("rustCheck", variants) { CheckTaskProcess(project, settings, it) }
        createVariantTasks("rustTest", variants) { TestTaskProcess(project, settings, it) }

        val benchList = variants
            .map { BuildVariant(project, settings, it.default, null, it.flavor) }
            .distinctBy { it.name }
        createVariantTasks("rustBench", benchList) { BenchTaskProcess(project, settings, it) }

        item.callback()
    }

    private fun createVariantTasks(
        category: String,
        variants: List<BuildVariant>,
        processGenerator: (BuildVariant) -> IRusticTaskProcess
    ) {
        val root = RusticTask.obtain(project.tasks, category, nothingTaskProcess)

        variants.forEach { variant ->
            val process = processGenerator(variant)
            if (variant.flavor == null) {
                val subTask = RusticTask.obtain(project.tasks, getTaskName(category, variant), process)
                root.dependsOn(subTask)
            } else {
                val subTask = RusticTask.obtain(project.tasks, getParentTaskName(category, variant), nothingTaskProcess)
                val subChildTask = RusticTask.obtain(project.tasks, getTaskName(category, variant), process)
                subTask.dependsOn(subChildTask)
                root.dependsOn(subTask)
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