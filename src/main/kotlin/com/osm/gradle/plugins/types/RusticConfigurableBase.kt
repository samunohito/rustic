package com.osm.gradle.plugins.types

import com.osm.gradle.plugins.util.objects.FieldPrintable
import groovy.lang.Closure
import org.gradle.util.Configurable

open class RusticConfigurableBase : FieldPrintable(), Configurable<Any> {
    var resolveStrategy = Closure.DELEGATE_FIRST

    override fun configure(closure: Closure<*>?): Any {
        closure?.also {
            it.delegate = this
            it.resolveStrategy = resolveStrategy
            it.run()
        }
        return this
    }
}
