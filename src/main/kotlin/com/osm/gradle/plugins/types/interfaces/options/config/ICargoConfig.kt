package com.osm.gradle.plugins.types.interfaces.options.config

import com.osm.gradle.plugins.types.interfaces.options.config.target.ITriple

interface ICargoConfig {
    //    val paths: Iterable<String?>?
    val cargoNew: ICargoNew
    val targetTriple: ITriple
    val registry: IRegistry
    val registries: Map<String?, String?>?
    val http: IHttp
    val build: IBuild
    val term: ITerm
    val net: INet
}