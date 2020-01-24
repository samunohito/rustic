package com.osm.gradle.plugins.types.interfaces.options

import com.osm.gradle.plugins.types.interfaces.options.option.IBase
import com.osm.gradle.plugins.types.interfaces.options.option.IBuild
import com.osm.gradle.plugins.types.interfaces.options.option.IJob
import com.osm.gradle.plugins.types.interfaces.options.option.IRelease

interface IBuildOptions : IBase, IBuild, IJob, IRelease {
    val buildPlan: Boolean?
    val outDir: String?
}