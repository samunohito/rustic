package com.osm.gradle.plugins.types.variants

import com.osm.gradle.plugins.log.LoggerSupport
import com.osm.gradle.plugins.types.ProjectSettings
import org.gradle.api.Project

abstract class PriorityResolveBase<T : Any>(protected val targets: List<T?>) : LoggerSupport {
    protected fun <U> resolve(func: (T) -> U?): U? = targets.filterNotNull().map(func).firstOrNull { it != null }
}