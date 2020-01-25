package com.osm.gradle.plugins.types.variants

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.config.BuildTypeConfig
import com.osm.gradle.plugins.types.config.DefaultConfig
import com.osm.gradle.plugins.types.config.ProductFlavorConfig
import com.osm.gradle.plugins.types.interfaces.IConfigBase
import com.osm.gradle.plugins.types.variants.options.*
import com.osm.gradle.plugins.util.string.toCamelCase
import org.gradle.api.Project

class BuildVariant(
    val project: Project,
    val settings: ProjectSettings,
    val default: DefaultConfig?,
    val build: BuildTypeConfig?,
    val flavor: ProductFlavorConfig?
) : PriorityResolveBase<IConfigBase>(listOf<IConfigBase?>(flavor, build, default)), IConfigBase {
    private val DEBUG = true

    override val name: String
        get() = targets
            .reversed()
            .filterNotNull().joinToString("") {
                it.name.toCamelCase('-').toCamelCase().capitalize()
            }
            .capitalize()

    override val environments: Map<String?, String?>?
        get() {
            val ret = HashMap<String?, String?>()
            targets.filterNotNull().reversed().forEach {
                it.environments?.also { map ->
                    ret.putAll(map)
                }
            }

            return ret
        }

    override val enabled: Boolean?
        get() = resolve { it.enabled }

    override val defaultOptions: DefaultOptions
        get() = DefaultOptions(
            this,
            listOf(flavor?.defaultOptions, build?.defaultOptions, default?.defaultOptions)
        )
    override val buildOptions: BuildOptions
        get() = BuildOptions(
            this,
            listOf(defaultOptions, flavor?.buildOptions, build?.buildOptions, default?.buildOptions)
        )
    override val checkOptions: CheckOptions
        get() = CheckOptions(
            this,
            listOf(defaultOptions, flavor?.checkOptions, build?.checkOptions, default?.checkOptions)
        )
    override val testOptions: TestOptions
        get() = TestOptions(
            this,
            listOf(defaultOptions, flavor?.testOptions, build?.testOptions, default?.testOptions)
        )
    override val benchOptions: BenchOptions
        get() = BenchOptions(
            this,
            listOf(defaultOptions, flavor?.benchOptions, build?.benchOptions, default?.benchOptions)
        )
    override val cleanOptions: CleanOptions
        get() = CleanOptions(
            this,
            listOf(defaultOptions, flavor?.cleanOptions, build?.cleanOptions, default?.cleanOptions)
        )

    val parentName: String
        get() {
            val tmpList = targets
                .reversed()
                .filterNotNull()
                .map {
                    it.name.toCamelCase('-').toCamelCase().capitalize()
                }

            return tmpList.take(tmpList.size - 1).joinToString("").capitalize()
        }

    constructor(project: Project, settings: ProjectSettings) : this(project, settings, null, null, null)

    override fun toString(): String {
        return if (DEBUG) {
            StringBuilder()
                .append("project = $settings").append("\n")
                .append("default = $default").append("\n")
                .append("build   = $build").append("\n")
                .append("flavor  = $flavor").append("\n")
                .toString()
        } else {
            super.toString()
        }
    }
}