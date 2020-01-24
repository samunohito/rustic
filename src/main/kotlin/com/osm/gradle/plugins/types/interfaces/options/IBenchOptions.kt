package com.osm.gradle.plugins.types.interfaces.options

import com.osm.gradle.plugins.types.interfaces.options.option.IBase
import com.osm.gradle.plugins.types.interfaces.options.option.IBuild
import com.osm.gradle.plugins.types.interfaces.options.option.IJob
import com.osm.gradle.plugins.types.interfaces.options.option.ITest

interface IBenchOptions : IBase, IBuild, IJob, ITest {
}