package com.osm.gradle.plugins

import com.osm.gradle.plugins.params.BuildVariant
import com.osm.gradle.plugins.params.project.ProjectBuildOptions
import com.osm.gradle.plugins.params.project.ProjectFlavorOptions
import com.osm.gradle.plugins.params.ProjectSettings
import com.osm.gradle.plugins.params.options.CleanOptions
import com.osm.gradle.plugins.params.project.ProjectDefaultOptions
import com.osm.gradle.plugins.process.*
import com.osm.gradle.plugins.task.RusticTask
import groovy.lang.Closure
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import com.osm.gradle.plugins.util.string.*

open class Rustic(val project: Project) {
    val defaultConfig: ProjectDefaultOptions =
        ProjectDefaultOptions("")
    val buildTypes: NamedDomainObjectContainer<ProjectBuildOptions> =
        project.container(ProjectBuildOptions::class.java)
    val flavors: NamedDomainObjectContainer<ProjectFlavorOptions> =
        project.container(ProjectFlavorOptions::class.java)
    val variants: NamedDomainObjectContainer<BuildVariant> =
        project.container(BuildVariant::class.java)
    val projectSettings: ProjectSettings =
        ProjectSettings()

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
    fun projectSettings(closure: Closure<*>) {
        projectSettings.configure(closure)
        createVariants()
    }

    /**
     * TODO:document
     */
    fun defaultConfig(closure: Closure<*>) {
        defaultConfig.configure(closure)
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

        // create build variants.
        if (flavors.isNotEmpty()) {
            buildTypes.all { buildOption ->
                flavors.all { flavorOption ->
                    variants.add(BuildVariant(projectSettings, defaultConfig, buildOption, flavorOption))
                }
            }
        }

        createTasks("rustBuild") { BuildTargetTaskProcess(this, it) }
        createTasks("rustCheck") { CheckTargetTaskProcess(this, it) }
        createTasks("rustTest") { TestTargetTaskProcess(this, it) }
        createTasks("rustBench") { BenchTargetTaskProcess(this, it) }

        createCleanTasks()
    }

    private fun createTasks(
        category: String,
        processGenerator: (variant: BuildVariant) -> RusticTaskProcessBase
    ) {
        val nothingTaskProcess = NothingTaskProcessBase(this, BuildVariant(projectSettings, defaultConfig))
        val build = RusticTask.obtain(project.tasks, category, nothingTaskProcess)

        if (variants.isNotEmpty()) {
            val buildSub = variants
                .mapNotNull { it.build }
                .associateWith {
                    val name = it.name.toCamelCase().toCamelCase('-').capitalize()
                    RusticTask.obtain(
                        project.tasks,
                        "$category${name}",
                        nothingTaskProcess
                    )
                }
            buildSub.forEach { (_, task) -> build.dependsOn(task) }

            variants.all { variant ->
                val name = variant.getName().toCamelCase().toCamelCase('-').capitalize()
                buildSub[variant.build]?.dependsOn(
                    RusticTask.obtain(
                        project.tasks,
                        "$category${name}",
                        processGenerator(variant)
                    )
                )
            }
        } else {
            buildTypes
                .map { processGenerator(BuildVariant(projectSettings, defaultConfig, it, null)) }
                .forEach {
                    val name = it.variant.getName().toCamelCase().toCamelCase('-').capitalize()
                    RusticTask.obtain(
                        project.tasks,
                        "$category${name}",
                        it
                    )
                }
        }
    }

    private fun createCleanTasks() {
        val prefix = "rustClean"

        val process = CleanTargetTaskProcess(this, BuildVariant(projectSettings, defaultConfig), CleanOptions())
        RusticTask.obtain(project.tasks, prefix, process)

//        val docOptions = CleanOptions()
//        docOptions.doc = true
//        CleanTargetTask.create("${prefix}Doc", this, CleanOptions())
//
//        val releaseOptions = CleanOptions()
//        releaseOptions.release = true
//        CleanTargetTask.create("${prefix}Release", this, CleanOptions())
//
//        val cleanTriple = TaskUtility.generate(project.tasks,("${prefix}Triple"))
//        variants.mapNotNull { it.flavor }.forEach {
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
}