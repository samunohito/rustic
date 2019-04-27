package com.osm.gradle.plugins.wrapper.builder.options

interface InitOptions {
    class Bin : SingleBase(), InitOptions {
        override val option: String = "--bin"
    }

    class Lib : SingleBase(), InitOptions {
        override val option: String = "--lib"
    }

    class Edition(name: String) : SingleBase(name), InitOptions {
        override val option: String = "--edition"
    }

    class Name(name: String) : SingleBase(name), InitOptions {
        override val option: String = "--name"
    }

    class Vcs(name: String) : SingleBase(name), InitOptions {
        override val option: String = "--vcs"
    }

    class Registry(registry: String) : SingleBase(registry), InitOptions {
        override val option: String = "--registry"
    }
}