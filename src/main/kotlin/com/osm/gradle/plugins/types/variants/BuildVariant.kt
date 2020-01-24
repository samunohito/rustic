package com.osm.gradle.plugins.types.variants

import com.osm.gradle.plugins.types.variants.options.CheckOptions
import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.config.BuildTypeConfig
import com.osm.gradle.plugins.types.config.DefaultConfig
import com.osm.gradle.plugins.types.config.ProductFlavorConfig
import com.osm.gradle.plugins.types.interfaces.IConfigBase
import com.osm.gradle.plugins.types.variants.options.*
import com.osm.gradle.plugins.types.variants.options.config.CargoConfig
import com.osm.gradle.plugins.util.other.Common
import com.osm.gradle.plugins.util.string.toCamelCase
import org.gradle.api.Project
import java.nio.file.Path
import java.nio.file.Paths

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
    override val targetDir: String?
        get() = resolve { it.targetDir }
    override val target: String?
        get() = resolve { it.target }
    override val features: Iterable<String?>?
        get() = resolve { it.features }

    override val buildOptions: BuildOptions
        get() = BuildOptions(listOf(flavor?.buildOptions, build?.buildOptions, default?.buildOptions))
    override val checkOptions: CheckOptions
        get() = CheckOptions(listOf(flavor?.checkOptions, build?.checkOptions, default?.checkOptions))
    override val testOptions: TestOptions
        get() = TestOptions(listOf(flavor?.testOptions, build?.testOptions, default?.testOptions))
    override val benchOptions: BenchOptions
        get() = BenchOptions(listOf(flavor?.benchOptions, build?.benchOptions, default?.benchOptions))
    override val cleanOptions: CleanOptions
        get() = CleanOptions(listOf(flavor?.cleanOptions, build?.cleanOptions, default?.cleanOptions))
    override val cargoConfig: CargoConfig
        get() = CargoConfig(this, listOf(flavor?.cargoConfig, build?.cargoConfig, default?.cargoConfig))

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

    val outputPath: Path
        get() {
            val intermediates = if (targetDir != null) {
                Paths.get(targetDir!!)
            } else {
                Paths.get(Common.getWorkingDirectory(project.projectDir, settings.projectLocation).toString(), "target")
            }

            val buildTypeString = "debug"//if (buildOptions.debug == false) "release" else "debug"

            return if (target != null) {
                Paths.get(intermediates.toString(), target, buildTypeString)
            } else {
                Paths.get(intermediates.toString(), buildTypeString)
            }
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