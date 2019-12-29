package com.osm.gradle.plugins.types

import com.osm.gradle.plugins.util.objects.FieldPrintable
import groovy.lang.Closure
import org.gradle.util.Configurable
import java.io.Serializable

open class RusticConfigurableBase : FieldPrintable(), Configurable<Any>, Serializable {
    var resolveStrategy = Closure.DELEGATE_FIRST

    override fun configure(closure: Closure<*>?): Any {
        closure?.also {
            it.delegate = this
            it.resolveStrategy = resolveStrategy
            it.run()
        }
        return this
    }

    companion object {
        @JvmField
        val serialVersionUID = 1L
    }
}
