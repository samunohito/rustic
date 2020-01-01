package com.osm.gradle.plugins.wrapper.builder.options.cargo

import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase

interface InitOptions {
    class Bin : SingleCommandOptionBase(),
        InitOptions {
        override val option: String = "--bin"
    }

    class Lib : SingleCommandOptionBase(),
        InitOptions {
        override val option: String = "--lib"
    }

    class Edition(name: String) : SingleCommandOptionBase(name),
        InitOptions {
        override val option: String = "--edition"
    }

    class Name(name: String) : SingleCommandOptionBase(name),
        InitOptions {
        override val option: String = "--name"
    }

    class Vcs(name: String) : SingleCommandOptionBase(name),
        InitOptions {
        override val option: String = "--vcs"
    }

    class Registry(registry: String) : SingleCommandOptionBase(registry),
        InitOptions {
        override val option: String = "--registry"
    }
}