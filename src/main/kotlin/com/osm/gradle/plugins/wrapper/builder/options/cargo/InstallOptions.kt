package com.osm.gradle.plugins.wrapper.builder.options.cargo

import com.osm.gradle.plugins.wrapper.builder.options.MultipleCommandOptionBase
import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase
import java.net.URL

interface InstallOptions {
    class Version(version: String) : SingleCommandOptionBase(version),
        InstallOptions {
        override val option: String = "--vesion"
    }

    class Git(url: URL) : SingleCommandOptionBase(url.toString()),
        InstallOptions {
        override val option: String = "--git"
    }

    class Branch(name: String) : SingleCommandOptionBase(name),
        InstallOptions {
        override val option: String = "--branch"
    }

    class Tag(name: String) : SingleCommandOptionBase(name),
        InstallOptions {
        override val option: String = "--tag"
    }

    class Rev(sha: String) : SingleCommandOptionBase(sha),
        InstallOptions {
        override val option: String = "--rev"
    }

    class Path(path: java.nio.file.Path) : SingleCommandOptionBase(path.toAbsolutePath().toString()),
        InstallOptions {
        override val option: String = "--path"
    }

    class List : SingleCommandOptionBase(),
        InstallOptions {
        override val option: String = "--list"
    }

    class Force : SingleCommandOptionBase(),
        InstallOptions {
        override val option: String = "--force"
    }

    class Bin(name: kotlin.collections.List<String?>) :
        MultipleCommandOptionBase(name.filterNotNull().filter { it.isNotEmpty() }),
        InstallOptions {
        override val option: String = "--bin"
    }

    class Bins : SingleCommandOptionBase(),
        InstallOptions {
        override val option: String = "--bins"
    }

    class Example(name: kotlin.collections.List<String?>) :
        MultipleCommandOptionBase(name.filterNotNull().filter { it.isNotEmpty() }),
        InstallOptions {
        override val option: String = "--example"
    }

    class Examples : SingleCommandOptionBase(),
        InstallOptions {
        override val option: String = "--examples"
    }

    class Root(name: java.nio.file.Path) : SingleCommandOptionBase(name.toAbsolutePath().joinToString { " " }),
        InstallOptions {
        override val option: String = "--root"
    }

    class Registry(registry: String) : SingleCommandOptionBase(registry),
        InstallOptions {
        override val option: String = "--registry"
    }
}