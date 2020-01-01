package com.osm.gradle.plugins.wrapper.builder.options.cargo

import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase

interface FeatureSelection {
    class Features(features: List<String?>) :
        SingleCommandOptionBase(features.filterNotNull().filter { it.isNotEmpty() }.joinToString { " " }),
        FeatureSelection {
        override val option: String = "--features"
    }

    class AllFeatures : SingleCommandOptionBase(),
        FeatureSelection {
        override val option: String = "--all-features"
    }

    class NoDefaultFeatures : SingleCommandOptionBase(),
        FeatureSelection {
        override val option: String = "--no-default-features"
    }
}