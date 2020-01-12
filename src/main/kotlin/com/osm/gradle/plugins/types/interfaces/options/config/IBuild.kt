package com.osm.gradle.plugins.types.interfaces.options.config

interface IBuild {
    /**
     * number of parallel jobs, defaults to # of CPUs
     */
    val jobs: Int?
    /**
     * the rust compiler tool
     */
    val rustc: String?
    /**
     * run this wrapper instead of `rustc`; useful to set up a
     * build cache tool such as `sccache`
     */
    val rustcWrapper: String?
    /**
     * the doc generator tool
     */
    val rustDoc: String?
    /**
     * build for the target triple (ignored by `cargo install`)
     */
    val target: String?
    /**
     * path of where to place all generated artifacts
     */
    val targetDir: String?
    /**
     * custom flags to pass to all compiler invocations
     */
    val rustFlags: Iterable<String?>?
    /**
     * custom flags to pass to rustdoc
     */
    val rustDocFlags: Iterable<String?>?
    /**
     * whether or not to enable incremental compilation
     * If `incremental` is not set, then the value from
     * the profile is used.
     */
    val incremental: Boolean?
    /**
     * full path for the base directory for targets in depfiles
     */
    val depInfoBaseDir: String?
}