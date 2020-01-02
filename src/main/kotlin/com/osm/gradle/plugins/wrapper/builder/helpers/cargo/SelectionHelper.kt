package com.osm.gradle.plugins.wrapper.builder.helpers.cargo

import com.osm.gradle.plugins.types.interfaces.IConfigBase
import com.osm.gradle.plugins.types.interfaces.options.ISelection
import com.osm.gradle.plugins.wrapper.builder.OptionBuilder
import com.osm.gradle.plugins.wrapper.builder.helpers.BuilderHelper
import com.osm.gradle.plugins.wrapper.builder.options.ICommandOption
import com.osm.gradle.plugins.wrapper.builder.options.cargo.TargetSelection

class SelectionHelper : BuilderHelper {
    override fun put(opt: IConfigBase?, builder: OptionBuilder) {
        put(opt?.targetSelection, builder)
    }

    fun put(sel: ISelection?, builder: OptionBuilder) {
        sel?.apply {
            select(builder, lib, null, { TargetSelection.Lib() }, null)
            select(builder, bins, bin, { TargetSelection.Bins() }, { TargetSelection.Bin(it) })
            select(builder, examples, example, { TargetSelection.Examples() }, { TargetSelection.Example(it) })
            select(builder, tests, test, { TargetSelection.Tests() }, { TargetSelection.Test(it) })
            select(builder, benches, bench, { TargetSelection.Benches() }, { TargetSelection.Bench(it) })
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