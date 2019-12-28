package com.osm.gradle.plugins.types.variants

import com.osm.gradle.pSelectionlugins.types.variants.options.CheckOptions
import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.config.BuildTypeConfig
import com.osm.gradle.plugins.types.config.DefaultConfig
import com.osm.gradle.plugins.types.config.ProductFlavorConfig
import com.osm.gradle.plugins.types.interfaces.IConfigBase
import com.osm.gradle.plugins.types.interfaces.options.*
import com.osm.gradle.plugins.types.variants.options.BenchOptions
import com.osm.gradle.plugins.types.variants.options.BuildOptions
import com.osm.gradle.plugins.types.variants.options.Selection
import com.osm.gradle.plugins.types.variants.options.TestOptions
import com.osm.gradle.plugins.util.string.toCamelCase
import java.util.concurrent.ConcurrentHashMap

class BuildVariant(
    val project: ProjectSettings,
    val default: DefaultConfig?,
    val build: BuildTypeConfig?,
    val flavor: ProductFlavorConfig?
) : PriorityResolveBase<IConfigBase>(listOf<IConfigBase?>(flavor, build, default)), IConfigBase {
    private val DEBUG = true
    private val internalEnvironments = ConcurrentHashMap<String, String>()

    override val enabled: Boolean?
        get() = resolve { it.enabled }
    override val name: String
        get() = targets
            .reversed()
            .filterNotNull().joinToString("") {
                it.name.toCamelCase('-').toCamelCase().capitalize()
            }
            .capitalize()
    override val targetDir: String?
        get() = resolve { it.targetDir }
    override val environments: MutableMap<String, String>
        get() {
            targets.filterNotNull().reversed().forEach {
                internalEnvironments.putAll(it.environments)
            }

            return internalEnvironments
        }
    override val target: String?
        get() = resolve { it.target }
    override val features: Iterable<String>?
        get() = resolve { it.features }
    override val targetSelection: ISelection =
        Selection(listOf<ISelection?>(flavor?.targetSelection, build?.targetSelection, default?.targetSelection))
    override val buildOptions: IBuildOptions =
        BuildOptions(listOf<IBuildOptions?>(flavor?.buildOptions, build?.buildOptions, default?.buildOptions))
    override val checkOptions: ICheckOptions =
        CheckOptions(listOf<ICheckOptions?>(flavor?.checkOptions, build?.checkOptions, default?.checkOptions))
    override val testOptions: ITestOptions =
        TestOptions(listOf<ITestOptions?>(flavor?.testOptions, build?.testOptions, default?.testOptions))
    override val benchOptions: IBenchOptions =
        BenchOptions(listOf<IBenchOptions?>(flavor?.benchOptions, build?.benchOptions, default?.benchOptions))

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

    constructor(project: ProjectSettings) : this(project, null, null, null)

    override fun toString(): String {
        return if (DEBUG) {
            StringBuilder()
                .append("project = $project").append("\n")
                .append("default = $default").append("\n")
                .append("build   = $build").append("\n")
                .append("flavor  = $flavor").append("\n")
                .toString()
        } else {
            super.toString()
        }
    }
}