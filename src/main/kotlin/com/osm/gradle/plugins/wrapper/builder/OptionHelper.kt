package com.osm.gradle.plugins.wrapper.builder

import com.osm.gradle.plugins.types.ProjectSettings
import com.osm.gradle.plugins.types.interfaces.IConfigBase
import com.osm.gradle.plugins.wrapper.builder.command.ICommandOption
import com.osm.gradle.plugins.wrapper.builder.options.CargoOptions
import java.nio.file.Paths

object OptionHelper {
    fun put(opt: IConfigBase?, builder: OptionBuilder) {
        opt?.apply {
            put(target, builder) { CargoOptions.Target(it) }
            put(targetDir, builder) { CargoOptions.TargetDir(Paths.get(it)) }
            put(features, builder) { CargoOptions.Features(it.toList()) }

//            benchOptions.apply {
//                put(noRun, builder, CargoOptions.NoRun::class.java) { CargoOptions.NoRun() }
//                put(noFailFast, builder, CargoOptions.NoFailFast::class.java) { CargoOptions.NoFailFast() }
//            }
//
//            buildOptions.apply {
//                put(debug, builder, CargoOptions.Release::class.java) { CargoOptions.Release() }
//                put(outputDirectory, builder) { CargoOptions.OutDir(Paths.get(it)) }
//            }
//
//            testOptions.apply {
//                put(noRun, builder, CargoOptions.NoRun::class.java) { CargoOptions.NoRun() }
//                put(noFailFast, builder, CargoOptions.NoFailFast::class.java) { CargoOptions.NoFailFast() }
//                put(doc, builder, CargoOptions.Doc::class.java) { CargoOptions.Doc() }
//            }
//
//            cleanOptions.apply {
//                put(doc, builder, CargoOptions.Doc::class.java) { CargoOptions.Doc() }
//                put(release, builder, CargoOptions.Release::class.java) { CargoOptions.Release() }
//                put(targetDir, builder) { CargoOptions.TargetDir(Paths.get(it)) }
//                put(triple, builder) { CargoOptions.Target(it) }
//            }
//
//            targetSelection.apply {
//                select(builder, lib, null, { CargoOptions.Lib() }, null)
//                select(builder, bins, bin, { CargoOptions.Bins() }, { CargoOptions.Bin(it) })
//                select(builder, examples, example, { CargoOptions.Examples() }, { CargoOptions.Example(it) })
//                select(builder, tests, test, { CargoOptions.Tests() }, { CargoOptions.Test(it) })
//                select(builder, benches, bench, { CargoOptions.Benches() }, { CargoOptions.Bench(it) })
//            }
        }
    }

    fun put(opt: ProjectSettings?, builder: OptionBuilder) {
        opt?.apply {
            jobs?.also { builder.put(CargoOptions.Jobs(it)) }
            manifestPath?.also { builder.put(CargoOptions.ManifestPath(Paths.get(it))) }
        }
    }

    private fun <T> put(value: T?, builder: OptionBuilder, generator: (T) -> ICommandOption) {
        value?.also {
            builder.put(generator(it))
        }
    }

    private fun <T : ICommandOption> put(
        value: Boolean?,
        builder: OptionBuilder,
        clazz: Class<T>,
        generator: (Boolean) -> T
    ) {
        value?.also {
            if (it) {
                builder.put(generator(it))
            } else {
                builder.removeIfMatchType(clazz)
            }
        }
    }

    private fun select(
        builder: OptionBuilder,
        all: Boolean?,
        each: Iterable<String?>?,
        onAll: (() -> ICommandOption),
        onEach: ((List<String>) -> ICommandOption)?
    ) {
        if (all == true) {
            builder.put(onAll.invoke())
        } else {
            each?.filterNotNull()?.filter { it.isNotEmpty() }?.toList()?.also {
                onEach?.invoke(it)?.also { result -> builder.put(result) }
            }
        }
    }
}