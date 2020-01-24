package com.osm.gradle.plugins.types.config.options.config

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.config.options.config.target.Triple
import com.osm.gradle.plugins.types.interfaces.options.config.ICargoConfig
import groovy.lang.Closure

open class CargoConfig : RusticConfigurableBase(), ICargoConfig {
    //    override var paths: Iterable<String?>? = ArrayList()
    override var registries: Map<String?, String?>? = HashMap()

    override var cargoNew: CargoNew = CargoNew()
    override var targetTriple: Triple = Triple()
    override var registry: Registry = Registry()
    override var http: Http = Http()
    override var build: Build = Build()
    override var term: Term = Term()
    override var net: Net = Net()

//    open fun paths(value: Iterable<String?>?) {
//        paths = value
//    }

    open fun registries(value: Map<String?, String?>?) {
        registries = value
    }

    open fun cargoNew(closure: Closure<*>) {
        cargoNew.configure(closure)
    }

    open fun targetTriple(closure: Closure<*>) {
        targetTriple.configure(closure)
    }

    open fun registry(closure: Closure<*>) {
        registry.configure(closure)
    }

    open fun http(closure: Closure<*>) {
        http.configure(closure)
    }

    open fun build(closure: Closure<*>) {
        build.configure(closure)
    }

    open fun term(closure: Closure<*>) {
        term.configure(closure)
    }

    open fun net(closure: Closure<*>) {
        net.configure(closure)
    }
}