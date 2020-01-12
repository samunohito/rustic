package com.osm.gradle.plugins.util.objects

abstract class FieldPrintable {
//    override fun toString(): String {
//        val fields = javaClass.declaredFields.toMutableList()
//
//        var superClazz: Class<*>? = javaClass.superclass
//        while (superClazz != null) {
//            fields.addAll(superClazz.declaredFields)
//            superClazz = superClazz.superclass
//        }
//
//        val ret = mutableListOf<String>()
//        fields.forEach {
//            it.isAccessible = true
//            if (it.get(this) == null) {
//                return@forEach
//            }
//
//            ret.add("${it.name}:${it.get(this)}")
//        }
//
//        return "[" + ret.joinToString(", ") + "]"
//    }
}