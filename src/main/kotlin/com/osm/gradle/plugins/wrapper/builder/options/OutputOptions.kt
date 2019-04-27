package com.osm.gradle.plugins.wrapper.builder.options

import java.nio.file.Path

interface OutputOptions {
    class TargetDir(targetDir: Path) : SingleBase(targetDir.toAbsolutePath().toString()), OutputOptions {
        override val option: String = "--target-dir"
    }

    class OutDir(outDir: Path) : SingleBase(outDir.toAbsolutePath().toString()), OutputOptions {
        override val option: String = "--out-dir"
    }
}