package com.osm.gradle.plugins.wrapper.builder.options

interface FeatureSelection {
    class Features(features: List<String?>) :
        SingleBase(features.filterNotNull().filter { it.isNotEmpty() }.joinToString { " " }), FeatureSelection {
        override val option: String = "--features"
    }

    class AllFeatures : SingleBase(), FeatureSelection {
        override val option: String = "--all-features"
    }

    class NoDefaultFeatures : SingleBase(), FeatureSelection {
        override val option: String = "--no-default-features"
    }
}