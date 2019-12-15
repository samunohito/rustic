package com.osm.gradle.plugins.process

import com.osm.gradle.plugins.Rustic
import com.osm.gradle.plugins.params.BuildVariant

abstract class RusticTaskProcessBase(val rustic: Rustic, val variant: BuildVariant) {
    abstract fun run()
}