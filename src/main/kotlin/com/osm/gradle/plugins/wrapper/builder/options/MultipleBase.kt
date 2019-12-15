package com.osm.gradle.plugins.wrapper.builder.options

abstract class MultipleBase(private val param: Iterable<String?>?) : Base() {
    override val multiple: Boolean = true

    override fun toString(): String {
        return param
            ?.filterNotNull()
            ?.filter { it.isNotEmpty() }
            ?.joinToString { "$option $it" }
            ?: ""
    }

    override fun toList(): List<String> {
        val p = param?.filterNotNull()
        return if (p != null) {
            listOfNotNull(option).plus(p)
        } else {
            listOfNotNull(option)
        }
    }
}