package com.osm.gradle.plugins

import com.osm.gradle.plugins.params.BuildVariant
import com.osm.gradle.plugins.params.ProjectBuildOptions
import com.osm.gradle.plugins.params.ProjectFlavorOptions
import com.osm.gradle.plugins.params.ProjectOptions
import com.osm.gradle.plugins.params.options.CleanOptions
import com.osm.gradle.plugins.task.*
import groovy.lang.Closure
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.Task

open class Rustic(val project: Project) {
    val buildTypes: NamedDomainObjectContainer<ProjectBuildOptions> =
        project.container(ProjectBuildOptions::class.java)
    val flavors: NamedDomainObjectContainer<ProjectFlavorOptions> =
        project.container(ProjectFlavorOptions::class.java)
    val variants: NamedDomainObjectContainer<BuildVariant> =
        project.container(BuildVariant::class.java)
    val projectOptions: ProjectOptions =
        ProjectOptions("")

    init {
        createVariants()
    }

    /**
     * Make settings that affect the entire project.
     * In addition to project configuration such as project location and manifest path,
     * You can also set default values​for buildType and the following items that can be set for flavors.
     * (the settings of buildType and flavor take precedence)
     * targetSelection, buildOptions, checkOptions, testOptions, and benchOptions.
     */
    fun projectOptions(closure: Closure<*>) {
        projectOptions.configure(closure)
        createVariants()
    }

    /**
     * Make settings that affect the build artifacts.
     * In addition, you can set targetSelection, buildOptions, checkOptions, testOptions, and benchOptions.
     * (The settings of flavors take precedence)
     */
    fun buildTypes(closure: Closure<*>) {
        buildTypes.configure(closure)
        createVariants()
    }

    /**
     * You can configure the toolchain (x86_64-linux-gnu-gcc and related tools) to be used in triple and build.
     * In addition, you can set targetSelection, buildOptions, checkOptions, testOptions, and benchOptions.
     */
    fun flavors(closure: Closure<*>) {
        flavors.configure(closure)
        createVariants()
    }

    private fun createVariants() {
        variants.clear()

        if (buildTypes.none { it.name == "debug" }) {
            val options = ProjectBuildOptions("debug")
            options.buildOptions.debug = true
            buildTypes.add(options)
        }

        if (buildTypes.none { it.name == "release" }) {
            val options = ProjectBuildOptions("release")
            options.buildOptions.debug = false
            buildTypes.add(options)
        }

        buildTypes.all { buildOption ->
            if (flavors.isNotEmpty()) {
                flavors.all { flavorOption ->
                    variants.add(BuildVariant(projectOptions, buildOption, flavorOption))
                }
            } else {
                variants.add(BuildVariant(projectOptions, buildOption, null))
            }
        }

        createTasks("rustBuild", BuildTargetTask::class.java)
        createTasks("rustCheck", CheckTargetTask::class.java)
        createTasks("rustTest", TestTargetTask::class.java)
        createTasks("rustBench", BenchTargetTask::class.java)

        createCleanTasks()
    }

    private fun <T : RustTaskBase> createTasks(category: String, taskClass: Class<T>) {
        val build = createPlainTask(category)
        val buildSub = variants
            .mapNotNull { it.build }
            .associate { Pair(it, createPlainTask("$category${it.name.capitalize()}")) }
        buildSub.forEach { (_, task) -> build.dependsOn(task) }

        variants.all { variant ->
            buildSub[variant.build]?.dependsOn(RustTaskBase.create(taskClass, category, this@Rustic, variant))
        }
    }

    private fun createPlainTask(name: String): Task {
        project.tasks.removeIf { it.name == name }
        val task = project.tasks.create(name)
        task.group = TASK_GROUP_NAME
        return task
    }

    private fun createCleanTasks() {
        val prefix = "rustClean"

        CleanTargetTask.create(prefix, this, CleanOptions())

        val docOptions = CleanOptions()
        docOptions.doc = true
        CleanTargetTask.create("${prefix}Doc", this, CleanOptions())

        val releaseOptions = CleanOptions()
        releaseOptions.release = true
        CleanTargetTask.create("${prefix}Release", this, CleanOptions())

        val cleanTriple = createPlainTask("${prefix}Triple")
        variants.mapNotNull { it.flavor }.forEach {
            val opt = CleanOptions()
            opt.triple = it.triple
            cleanTriple.dependsOn(
                CleanTargetTask.create(
                    "${cleanTriple.name}${it.name.capitalize()}",
                    this@Rustic,
                    opt
                )
            )
        }
    }
}