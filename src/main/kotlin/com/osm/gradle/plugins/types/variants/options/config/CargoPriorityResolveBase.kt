package com.osm.gradle.plugins.types.variants.options.config

import com.osm.gradle.plugins.types.variants.PriorityResolveBase
import java.util.*

abstract class CargoPriorityResolveBase<T : Any>(targets: List<T?>) : PriorityResolveBase<T>(targets) {
    protected abstract val environmentPrefix: String
    protected abstract val environmentPropertyMapper: Map<String, () -> Any?>

    fun toEnvironmentMap(): Map<String, String> {
        return environmentPropertyMapper
            .map { AbstractMap.SimpleEntry(it.key, it.value()) }
            .filter { it.value != null }
            .associateBy({ environmentPrefix + "_" + it.key }, { it.value!!.toString() })
    }
}