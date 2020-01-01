package com.osm.gradle.plugins.wrapper.builder.options.cargo

import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase

interface MiscellaneousOptions {
    class Jobs(jobs: Int) : SingleCommandOptionBase(jobs.toString()),
        MiscellaneousOptions {
        override val option: String = "--jobs"
    }
}
