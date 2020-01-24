package com.osm.gradle.plugins.types.config.options

import com.osm.gradle.plugins.types.RusticConfigurableBase
import com.osm.gradle.plugins.types.interfaces.options.IBenchOptions

class BenchOptions : RusticConfigurableBase(), IBenchOptions {
    override var all: Boolean?= null
    override var allFeatures: Boolean?= null
    override var allTargets: Boolean?= null
    override var bench: Iterable<String?>?= null
    override var benches: Boolean?= null
    override var bin: Iterable<String?>?= null
    override var bins: Boolean?= null
    override var color: String?= null
    override var example: Iterable<String?>?= null
    override var examples: Boolean?= null
    override var exclude: Iterable<String?>?​
    override var features: String?= null
    override var frozen: Boolean?= null
    override var help: Boolean?= null
    override var jobs: Int?= null
    override var lib: Boolean?= null
    override var locked: Boolean?= null
    override var manifestPath: String?= null
    override var messageFormat: String?= null
    override var noDefaultFeatures: Boolean?= null
    override var noFailFast: Boolean?= null
    override var noRun: Boolean?= null
    override var offline: Boolean?= null
    override var packageName: Iterable<String?>?​= null
    override var quiet: Boolean?= null
    override var target: String?= null
    override var targetDir: String?= null
    override var test: Iterable<String?>?​= null
    override var tests: Boolean?= null
    override var verbose: Boolean?= null
    override var workspace: Boolean?= null
}