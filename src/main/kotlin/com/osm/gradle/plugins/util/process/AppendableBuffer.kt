package com.osm.gradle.plugins.util.process

class AppendableBuffer : Appendable {
    private val textCache = StringBuilder()
    private val buffers = ArrayList<Appendable>()

    constructor() {
        buffers.add(textCache)
    }

    constructor(external: Iterable<Appendable>) : this() {
        buffers.addAll(external)
    }

    override fun append(csq: CharSequence?): Appendable {
        buffers.forEach { it.append(csq) }
        return this
    }

    override fun append(csq: CharSequence?, start: Int, end: Int): Appendable {
        buffers.forEach { it.append(csq, start, end) }
        return this
    }

    override fun append(c: Char): Appendable {
        buffers.forEach { it.append(c) }
        return this
    }

    override fun toString(): String {
        return textCache.toString()
    }

    fun clear() {
        textCache.clear()
    }
}