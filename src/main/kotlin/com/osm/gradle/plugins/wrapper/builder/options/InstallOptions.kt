package com.osm.gradle.plugins.wrapper.builder.options

import java.net.URL

interface InstallOptions {
    class Version(version: String) : SingleBase(version), InstallOptions {
        override val option: String = "--vesion"
    }

    class Git(url: URL) : SingleBase(url.toString()), InstallOptions {
        override val option: String = "--git"
    }

    class Branch(name: String) : SingleBase(name), InstallOptions {
        override val option: String = "--branch"
    }

    class Tag(name: String) : SingleBase(name), InstallOptions {
        override val option: String = "--tag"
    }

    class Rev(sha: String) : SingleBase(sha), InstallOptions {
        override val option: String = "--rev"
    }

    class Path(path: java.nio.file.Path) : SingleBase(path.toAbsolutePath().toString()), InstallOptions {
        override val option: String = "--path"
    }

    class List : SingleBase(), InstallOptions {
        override val option: String = "--list"
    }

    class Force : SingleBase(), InstallOptions {
        override val option: String = "--force"
    }

    class Bin(name: kotlin.collections.List<String?>) : MultipleBase(name.filterNotNull().filter { it.isNotEmpty() }),
        InstallOptions {
        override val option: String = "--bin"
    }

    class Bins : SingleBase(), InstallOptions {
        override val option: String = "--bins"
    }

    class Example(name: kotlin.collections.List<String?>) :
        MultipleBase(name.filterNotNull().filter { it.isNotEmpty() }), InstallOptions {
        override val option: String = "--example"
    }

    class Examples : SingleBase(), InstallOptions {
        override val option: String = "--examples"
    }

    class Root(name: java.nio.file.Path) : SingleBase(name.toAbsolutePath().joinToString { " " }), InstallOptions {
        override val option: String = "--root"
    }

    class Registry(registry: String) : SingleBase(registry), InstallOptions {
        override val option: String = "--registry"
    }
}