package com.osm.gradle.plugins.types.interfaces.options

interface ITestOptions {
    val all: Boolean?
    val allFeatures: Boolean?
    val allTargets: Boolean?
    val bench: Iterable<String?>?
    val benches: Boolean?
    val bin: Iterable<String?>?
    val bins: Boolean?
    val color: String?
    val doc: Boolean?
    val example: Iterable<String?>?
    val examples: Boolean?
    val exclude: Iterable<String?>?
    val features: String?
    val frozen: Boolean?
    val help: Boolean?
    val lib: Boolean?
    val locked: Boolean?
    val manifestPath: String?
    val messageFormat: String?
    val noDefaultFeatures: Boolean?
    val noFailFast: Boolean?
    val noRun: Boolean?
    val offline: Boolean?
    val packageName: Iterable<String?>?​
    val quiet: Boolean?
    val release: Boolean?
    val target: String?
    val targetDir: String?
    val test: Iterable<String?>?
    val tests: Boolean?
    val verbose: Boolean?
    val workspace: Boolean?
}