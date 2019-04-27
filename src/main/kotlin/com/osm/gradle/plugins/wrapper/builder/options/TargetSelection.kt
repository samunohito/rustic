package com.osm.gradle.plugins.wrapper.builder.options

interface TargetSelection {
    class Lib : SingleBase(), TargetSelection {
        override val option: String = "--lib"
    }

    class Bin(name: List<String>) : MultipleBase(name), TargetSelection {
        override val option: String = "--bin"
    }

    class Bins : SingleBase(), TargetSelection {
        override val option: String = "--bins"
    }

    class Example(name: List<String>) : MultipleBase(name), TargetSelection {
        override val option: String = "--example"
    }

    class Examples : SingleBase(), TargetSelection {
        override val option: String = "--exmaples"
    }

    class Test(name: List<String>) : MultipleBase(name), TargetSelection {
        override val option: String = "--test"
    }

    class Tests : SingleBase(), TargetSelection {
        override val option: String = "--tests"
    }

    class Bench(name: List<String>) : MultipleBase(name), TargetSelection {
        override val option: String = "--bench"
    }

    class Benches : SingleBase(), TargetSelection {
        override val option: String = "--benches"
    }

    class AllTargets : SingleBase(), TargetSelection {
        override val option: String = "--all-targets"
    }

    class Doc : SingleBase(), TargetSelection {
        override val option: String = "--doc"
    }
}