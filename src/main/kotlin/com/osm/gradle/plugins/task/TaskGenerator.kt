package com.osm.gradle.plugins.task

import com.osm.gradle.plugins.process.*
import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.config.options.CleanOptions
import com.osm.gradle.plugins.types.variants.BuildVariant
import org.gradle.api.DomainObjectSet
import org.gradle.api.Project

class TaskGenerator(
    private val project: Project,
    private val settings: ProjectSettings
) {
    private val nothingTaskProcess = NothingTaskProcess()

    init {
        createCleanTasks()
    }

    fun createTasks(variants: List<BuildVariant>) {
        variants.forEach { createTask(it) }
    }

    fun createTask(variant: BuildVariant) {
        createTask("rustBuild", variant, BuildTargetTaskProcess(project, settings, variant))
        createTask("rustCheck", variant, CheckTargetTaskProcess(project, settings, variant))
        createTask("rustTest", variant, TestTargetTaskProcess(project, settings, variant))
        createTask("rustBench", variant, BenchTargetTaskProcess(project, settings, variant))
    }

    private fun createTask(category: String, variant: BuildVariant, process: IRusticTaskProcess) {
        val root = RusticTask.obtain(project.tasks, category, nothingTaskProcess)
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

    private fun createCleanTasks() {
        val prefix = "rustClean"

        val process = CleanTargetTaskProcess(project, settings, BuildVariant(settings), CleanOptions())
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
}