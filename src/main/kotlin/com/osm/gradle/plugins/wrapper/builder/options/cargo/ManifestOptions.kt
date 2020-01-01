package com.osm.gradle.plugins.wrapper.builder.options.cargo

import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase
import java.nio.file.Path

interface ManifestOptions {
    class ManifestPath(path: Path) : SingleCommandOptionBase(path.toAbsolutePath().toString()),
        ManifestOptions {
        override val option: String = "--manifest-path"
    }

    class Frozen : SingleCommandOptionBase(),
        ManifestOptions {
        override val option: String = "--frozen"
    }

    class Locked : SingleCommandOptionBase(),
        ManifestOptions {
        override val option: String = "--locked"
    }
}