package com.osm.gradle.plugins.wrapper.builder.options.cargo

import com.osm.gradle.plugins.wrapper.builder.options.MultipleCommandOptionBase
import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase

interface PackageSelection {
    class Package(name: List<String>) : MultipleCommandOptionBase(name),
        PackageSelection {
        override val option: String = "--package"
    }

    class All : SingleCommandOptionBase(),
        PackageSelection {
        override val option: String = "--all"
    }

    class Exclude(name: List<String>) : MultipleCommandOptionBase(name),
        PackageSelection {
        override val option: String = "--exclude"
    }
}