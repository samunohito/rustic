package com.osm.gradle.plugins.params

import groovy.lang.Closure
import org.gradle.util.Configurable
import java.lang.StringBuilder
import java.lang.reflect.Field

open class RusticConfigurableBase : Configurable<Any> {
    var resolveStrategy = Closure.DELEGATE_FIRST

    override fun toString(): String {
        val fields = javaClass.declaredFields.toMutableList()

        var superClazz: Class<*>? = javaClass.superclass
        while (superClazz != null) {
            fields.addAll(superClazz.declaredFields)
            superClazz = superClazz.superclass
        }

        val ret = mutableListOf<String>()
        fields.forEach {
            it.isAccessible = true
            if (it.get(this) == null) {
                return@forEach
            }

            ret.add("${it.name}:${it.get(this)}")
        }

        return "[" + ret.joinToString(", ") + "]"
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
