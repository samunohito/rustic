package com.osm.gradle.plugins.types.variants

import com.osm.gradle.plugins.log.LoggerSupport

abstract class PriorityResolveBase<T : Any>(protected val targets: List<T?>) : LoggerSupport {
    protected fun <U> resolve(func: (T) -> U?): U? = targets.filterNotNull().map(func).firstOrNull { it != null }
}