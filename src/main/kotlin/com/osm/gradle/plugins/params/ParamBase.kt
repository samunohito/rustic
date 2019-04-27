package com.osm.gradle.plugins.params

import groovy.lang.Closure
import org.gradle.util.Configurable

open class ParamBase : Configurable<Any> {
    var resolveStrategy = Closure.DELEGATE_ONLY

    override fun toString(): String {
        return "[" + javaClass.fields.joinToString(", ") { "${it.name} = ${it.get(this)}" } + "]"
    }

    override fun configure(closure: Closure<*>?): Any {
        closure?.also {
            it.delegate = this
            it.resolveStrategy = resolveStrategy
            it.run()
        }
        return this
    }
}
