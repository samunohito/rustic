package com.osm.gradle.plugins.wrapper.builder.options

interface PackageSelection {
    class Package(name: List<String>) : MultipleBase(name), PackageSelection {
        override val option: String = "--package"
    }

    class All : SingleBase(), PackageSelection {
        override val option: String = "--all"
    }

    class Exclude(name: List<String>) : MultipleBase(name), PackageSelection {
        override val option: String = "--exclude"
    }
}