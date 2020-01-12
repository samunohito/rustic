package com.osm.gradle.plugins.types.interfaces.options.config

import com.osm.gradle.plugins.types.interfaces.options.config.registries.IName
import com.osm.gradle.plugins.types.interfaces.options.config.target.ITriple

interface ICargoConfig {
    val paths: Iterable<String?>?
    val cargoNew: ICargoNew
    val target: Iterable<ITriple>
    val registry: IRegistry
    val registries: Iterable<IName>
    val http: IHttp
    val build: IBuild
    val term: ITerm
    val net: INet
}