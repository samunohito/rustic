package com.osm.gradle.plugins.types.variants.options.config

import com.osm.gradle.plugins.types.interfaces.options.config.ICargoConfig
import com.osm.gradle.plugins.types.variants.BuildVariant
import com.osm.gradle.plugins.types.variants.PriorityResolveBase
import com.osm.gradle.plugins.types.variants.options.config.target.Triple

class CargoConfig(val variant: BuildVariant, val args: List<ICargoConfig?>) :
    PriorityResolveBase<ICargoConfig>(args), ICargoConfig {
    //    override val paths: Iterable<String?>?
//        get() = resolve { it.paths }

    override val registries: Map<String, String>?
        get() {
            val ret = HashMap<String, String>()
            targets.reversed().mapNotNull { it?.registries }.forEach {
                ret.putAll(it)
            }
            return ret
        }

    override val cargoNew: CargoNew
        get() = CargoNew(args.map { it?.cargoNew })
    override val targetTriple: Triple
        get() = Triple(variant.target, args.map { it?.targetTriple })
    override val registry: Registry
        get() = Registry(args.map { it?.registry })
    override val http: Http
        get() = Http(args.map { it?.http })
    override val build: Build
        get() = Build(args.map { it?.build })
    override val term: Term
        get() = Term(args.map { it?.term })
    override val net: Net
        get() = Net(args.map { it?.net })

    fun getConfigMap(): Map<String, String> {
        val ret = HashMap<String, String>()

        registries?.also { registries ->
            registries
                .asSequence()
                .associateBy({ "CARGO_REGISTRIES_" + it.key.toUpperCase() }, { it.value })
                .forEach {
                    ret[it.key] = it.value
                }
        }

        listOf(cargoNew, targetTriple, registry, http, build, term, net)
            .map { it.toEnvironmentMap() }
            .filter { it.isNotEmpty() }
            .forEach { map ->
                map.forEach {
                    ret["CARGO_" + it.key] = it.value
                }
            }

        return ret
    }
}