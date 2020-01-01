package com.osm.gradle.plugins.wrapper.builder.options.cargo

import com.osm.gradle.plugins.wrapper.builder.options.MultipleCommandOptionBase
import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase

interface TargetSelection {
    class Lib : SingleCommandOptionBase(),
        TargetSelection {
        override val option: String = "--lib"
    }

    class Bin(name: List<String>) : MultipleCommandOptionBase(name),
        TargetSelection {
        override val option: String = "--bin"
    }

    class Bins : SingleCommandOptionBase(),
        TargetSelection {
        override val option: String = "--bins"
    }

    class Example(name: List<String>) : MultipleCommandOptionBase(name),
        TargetSelection {
        override val option: String = "--example"
    }

    class Examples : SingleCommandOptionBase(),
        TargetSelection {
        override val option: String = "--exmaples"
    }

    class Test(name: List<String>) : MultipleCommandOptionBase(name),
        TargetSelection {
        override val option: String = "--test"
    }

    class Tests : SingleCommandOptionBase(),
        TargetSelection {
        override val option: String = "--tests"
    }

    class Bench(name: List<String>) : MultipleCommandOptionBase(name),
        TargetSelection {
        override val option: String = "--bench"
    }

    class Benches : SingleCommandOptionBase(),
        TargetSelection {
        override val option: String = "--benches"
    }

    class AllTargets : SingleCommandOptionBase(),
        TargetSelection {
        override val option: String = "--all-targets"
    }

    class Doc : SingleCommandOptionBase(),
        TargetSelection {
        override val option: String = "--doc"
    }
}