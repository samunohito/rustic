package com.osm.gradle.plugins.wrapper.builder.options

interface MiscellaneousOptions {
    class Jobs(jobs: Int) : SingleBase(jobs.toString()), MiscellaneousOptions {
        override val option: String = "--jobs"
    }
}
