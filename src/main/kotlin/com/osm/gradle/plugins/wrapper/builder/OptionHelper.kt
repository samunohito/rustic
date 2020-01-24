package com.osm.gradle.plugins.wrapper.builder

import com.osm.gradle.plugins.types.interfaces.options.IBuildOptions
import com.osm.gradle.plugins.types.interfaces.options.ICheckOptions
import com.osm.gradle.plugins.types.interfaces.options.option.*
import com.osm.gradle.plugins.wrapper.builder.command.ICommandOption
import com.osm.gradle.plugins.wrapper.builder.options.CargoOptions
import java.nio.file.Paths

object OptionHelper {
    fun put(opt: Any, builder: OptionBuilder) {
        if (opt is IBase) {
            putInternal(opt, builder)
        }

        if (opt is IBuild) {
            putInternal(opt, builder)
        }

        if (opt is IDoc) {
            putInternal(opt, builder)
        }

        if (opt is IJob) {
            putInternal(opt, builder)
        }

        if (opt is IRelease) {
            putInternal(opt, builder)
        }

        if (opt is ITest) {
            putInternal(opt, builder)
        }

        if (opt is IBuildOptions) {
            putInternal(opt, builder)
        }

        if (opt is ICheckOptions) {
            putInternal(opt, builder)
        }
    }

    private fun putInternal(opt: IBuildOptions, builder: OptionBuilder) {
        opt.apply {
            putInternal(buildPlan, builder, CargoOptions.BuildPlan::class.java) { CargoOptions.BuildPlan() }
            putInternal(outDir, builder) { CargoOptions.OutDir(Paths.get(it)) }
        }
    }

    private fun putInternal(opt: ICheckOptions, builder: OptionBuilder) {
        opt.apply {
            putInternal(profile, builder) { CargoOptions.Profile() }
        }
    }

    private fun putInternal(opt: IBase, builder: OptionBuilder) {
        opt.apply {
            putInternal(color, builder) { CargoOptions.Color(CargoOptions.Color.Type.from(it)) }
            putInternal(frozen, builder, CargoOptions.Frozen::class.java) { CargoOptions.Frozen() }
            putInternal(locked, builder, CargoOptions.Locked::class.java) { CargoOptions.Locked() }
            putInternal(manifestPath, builder) { CargoOptions.ManifestPath(Paths.get(it)) }
            putInternal(offline, builder, CargoOptions.Offline::class.java) { CargoOptions.Offline() }
            putInternal(packageName, builder) { CargoOptions.Package(it) }
            putInternal(quiet, builder, CargoOptions.Quiet::class.java) { CargoOptions.Quiet() }
            putInternal(target, builder) { CargoOptions.Target(it) }
            putInternal(targetDir, builder) { CargoOptions.TargetDir(Paths.get(it)) }
            putInternal(verbose, builder, CargoOptions.Verbose::class.java) { CargoOptions.Verbose() }
        }
    }

    private fun putInternal(opt: IBuild, builder: OptionBuilder) {
        opt.apply {
            select(builder, lib, null, { CargoOptions.Lib() }, null)
            select(builder, bins, bin, { CargoOptions.Bins() }, { CargoOptions.Bin(it) })
            select(builder, examples, example, { CargoOptions.Examples() }, { CargoOptions.Example(it) })
            select(builder, tests, test, { CargoOptions.Tests() }, { CargoOptions.Test(it) })
            select(builder, benches, bench, { CargoOptions.Benches() }, { CargoOptions.Bench(it) })

            putInternal(all, builder, CargoOptions.All::class.java) { CargoOptions.All() }
            putInternal(allFeatures, builder, CargoOptions.AllFeatures::class.java) { CargoOptions.AllFeatures() }
            putInternal(allTargets, builder, CargoOptions.AllTargets::class.java) { CargoOptions.AllTargets() }
            putInternal(exclude, builder) { CargoOptions.Exclude(it) }
            putInternal(features, builder) { CargoOptions.Features(it) }
            putInternal(messageFormat, builder) { CargoOptions.MessageFormat(CargoOptions.MessageFormat.Type.from(it)) }
            putInternal(noDefaultFeatures, builder, CargoOptions.NoDefaultFeatures::class.java) {
                CargoOptions.NoDefaultFeatures()
            }
            putInternal(workspace, builder, CargoOptions.Workspace::class.java) { CargoOptions.Workspace() }
        }
    }

    private fun putInternal(opt: IDoc, builder: OptionBuilder) {
        opt.apply {
            putInternal(doc, builder, CargoOptions.Doc::class.java) { CargoOptions.Doc() }
        }
    }

    private fun putInternal(opt: IJob, builder: OptionBuilder) {
        opt.apply {
            putInternal(jobs, builder) { CargoOptions.Jobs(it) }
        }
    }

    private fun putInternal(opt: IRelease, builder: OptionBuilder) {
        opt.apply {
            putInternal(release, builder, CargoOptions.Release::class.java) { CargoOptions.Release() }
        }
    }

    private fun putInternal(opt: ITest, builder: OptionBuilder) {
        opt.apply {
            putInternal(noFailFast, builder, CargoOptions.NoFailFast::class.java) { CargoOptions.NoFailFast() }
            putInternal(noRun, builder, CargoOptions.NoRun::class.java) { CargoOptions.NoRun() }
        }
    }

    private fun <T> putInternal(value: T?, builder: OptionBuilder, generator: (T) -> ICommandOption) {
        value?.also {
            builder.put(generator(it))
        }
    }

    private fun <T : ICommandOption> putInternal(
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