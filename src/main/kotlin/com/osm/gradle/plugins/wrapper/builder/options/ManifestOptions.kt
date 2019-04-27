package com.osm.gradle.plugins.wrapper.builder.options

import java.nio.file.Path

interface ManifestOptions {
    class ManifestPath(path: Path) : SingleBase(path.toAbsolutePath().toString()), ManifestOptions {
        override val option: String = "--manifest-path"
    }

    class Frozen : SingleBase(), ManifestOptions {
        override val option: String = "--frozen"
    }

    class Locked : SingleBase(), ManifestOptions {
        override val option: String = "--locked"
    }
}