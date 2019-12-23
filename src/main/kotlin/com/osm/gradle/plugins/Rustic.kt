package com.osm.gradle.plugins

import com.osm.gradle.plugins.params.BuildVariant
import com.osm.gradle.plugins.params.ProjectSettings
import com.osm.gradle.plugins.params.options.CleanOptions
import com.osm.gradle.plugins.params.project.ProjectBuildOptions
import com.osm.gradle.plugins.params.project.ProjectDefaultOptions
import com.osm.gradle.plugins.params.project.ProjectFlavorOptions
import com.osm.gradle.plugins.process.*
import com.osm.gradle.plugins.task.RusticTask
import com.osm.gradle.plugins.util.string.toCamelCase
import groovy.lang.Closure
import org.gradle.api.Action
import org.gradle.api.DomainObjectSet
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.internal.CollectionCallbackActionDecorator
import org.gradle.api.internal.DefaultDomainObjectSet
import org.gradle.api.internal.DefaultNamedDomainObjectCollection

/**
 * https://mrhaki.blogspot.com/2016/02/gradle-goodness-using-nested-domain.html
 * https://mrhaki.blogspot.com/2016/02/gradle-goodness-create-objects-with-dsl.html
 * https://henteko07.hatenablog.com/entry/2013/11/29/070537
 */
open class Rustic(val project: Project) {
    val defaultConfig: ProjectDefaultOptions =
        ProjectDefaultOptions("")
    val buildTypes: NamedDomainObjectContainer<ProjectBuildOptions> =
        project.container(ProjectBuildOptions::class.java)
    val flavors: NamedDomainObjectContainer<ProjectFlavorOptions> =
        project.container(ProjectFlavorOptions::class.java)
    val projectSettings: ProjectSettings =
        ProjectSettings("")
    val variants: DomainObjectSet<BuildVariant> =
        DefaultDomainObjectSet<BuildVariant>(BuildVariant::class.java, CollectionCallbackActionDecorator.NOOP)

    init {
        val debugOptions = ProjectBuildOptions("debug")
        debugOptions.buildOptions.debug = true
        buildTypes.add(debugOptions)

        val releaseOptions = ProjectBuildOptions("release")
        releaseOptions.buildOptions.debug = false
        buildTypes.add(releaseOptions)
    }

    /**
     * Make settings that affect the entire project.
     * In addition to project configuration such as project location and manifest path,
     * You can also set default values​for buildType and the following items that can be set for flavors.
     * (the settings of buildType and flavor take precedence)
     * targetSelection, buildOptions, checkOptions, testOptions, and benchOptions.
     */
    fun projectSettings(closure: Closure<*>) {
        log("projectSettings start")
        projectSettings.configure(closure)
        log("projectSettings end")
    }

    /**
     * TODO:document
     */
    fun defaultConfig(closure: Closure<*>) {
        log("defaultConfig start")
        defaultConfig.configure(closure)
        log("defaultConfig end")
    }

    /**
     * Make settings that affect the build artifacts.
     * In addition, you can set targetSelection, buildOptions, checkOptions, testOptions, and benchOptions.
     * (The settings of flavors take precedence)
     */
    fun buildTypes(closure: Closure<*>) {
        log("buildTypes start")
        buildTypes.configure(closure)
        log("buildTypes end")
    }

    /**
     * You can configure the toolchain (x86_64-linux-gnu-gcc and related tools) to be used in triple and build.
     * In addition, you can set targetSelection, buildOptions, checkOptions, testOptions, and benchOptions.
     */
    fun flavors(closure: Closure<*>) {
        log("flavors start")
        flavors.configure(closure)
        log("flavors end")
    }


    fun createVariants() {
        variants.clear()

        createTasks("rustBuild") { BuildTargetTaskProcess(this, it) }
        createTasks("rustCheck") { CheckTargetTaskProcess(this, it) }
        createTasks("rustTest") { TestTargetTaskProcess(this, it) }
        createTasks("rustBench") { BenchTargetTaskProcess(this, it) }

        createCleanTasks()

        buildTypes.all { buildOption ->
            variants.add(BuildVariant(projectSettings, defaultConfig, buildOption, null))
        }

        flavors.all { flavorOption ->
            buildTypes.all { buildOption ->
                variants.add(BuildVariant(projectSettings, defaultConfig, buildOption, flavorOption))
            }
        }
    }

    private fun createTasks(
        category: String,
        processGenerator: (variant: BuildVariant) -> RusticTaskProcessBase
    ) {
        val nothingTaskProcess = NothingTaskProcessBase(this, BuildVariant(projectSettings, defaultConfig))
        val build = RusticTask.obtain(project.tasks, category, nothingTaskProcess)

        variants.all {
            val subName = category + it.build?.name?.toCamelCase('-')?.toCamelCase()?.capitalize()
            val subTask = RusticTask.obtain(project.tasks, subName, nothingTaskProcess)
            if (it.flavor == null) {
                build.dependsOn(subTask)
            } else {
                val subChildName = subName + it.getName().toCamelCase('-').toCamelCase().capitalize()
                val subChildTask = RusticTask.obtain(project.tasks, subChildName, processGenerator(it))
                subTask.dependsOn(subChildTask)
            }
        }
    }

    private fun createCleanTasks() {
        val prefix = "rustClean"

        val process = CleanTargetTaskProcess(this, BuildVariant(projectSettings, defaultConfig), CleanOptions())
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