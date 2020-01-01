package com.osm.gradle.plugins.wrapper.builder.options.cargo

import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase
import java.nio.file.Path

interface OutputOptions {
    class TargetDir(targetDir: Path) : SingleCommandOptionBase(targetDir.toAbsolutePath().toString()),
        OutputOptions {
        override val option: String = "--target-dir"
    }

    class OutDir(outDir: Path) : SingleCommandOptionBase(outDir.toAbsolutePath().toString()),
        OutputOptions {
        override val option: String = "--out-dir"
    }
}