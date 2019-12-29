package com.osm.gradle.plugins.util.objects

import groovy.lang.GroovyObjectSupport

abstract class FieldPrintable : GroovyObjectSupport() {
    protected val DEPTH = 30
    override fun toString(): String {
        val fields = javaClass.declaredFields.toMutableList()

        var superClazz: Class<*>? = javaClass.superclass
        while (superClazz != null && DEPTH > fields.size) {
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
}