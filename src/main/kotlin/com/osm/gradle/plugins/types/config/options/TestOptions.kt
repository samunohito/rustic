package com.osm.gradle.plugins.types.config.options

import com.osm.gradle.plugins.types.config.options.option.Base
import com.osm.gradle.plugins.types.interfaces.options.ITestOptions

class TestOptions : Base(), ITestOptions {
    override var all: Boolean? = null
    override var allFeatures: Boolean? = null
    override var allTargets: Boolean? = null
    override var bench: Iterable<String?>? = null
    override var benches: Boolean? = null
    override var bin: Iterable<String?>? = null
    override var bins: Boolean? = null
    override var example: Iterable<String?>? = null
    override var examples: Boolean? = null
    override var exclude: Iterable<String?>? = null
    override var features: Iterable<String?>? = null
    override var lib: Boolean? = null
    override var messageFormat: String? = null
    override var noDefaultFeatures: Boolean? = null
    override var test: Iterable<String?>? = null
    override var tests: Boolean? = null
    override var workspace: Boolean? = null
    override var doc: Boolean? = null
    override var release: Boolean? = null
    override var noFailFast: Boolean? = null
    override var noRun: Boolean? = null

    fun all(value: Boolean?) {
        all = value
    }

    fun allFeatures(value: Boolean?) {
        allFeatures = value
    }

    fun allTargets(value: Boolean?) {
        allTargets = value
    }

    fun bench(value: Iterable<String?>?) {
        bench = value
    }

    fun benches(value: Boolean?) {
        benches = value
    }

    fun bin(value: Iterable<String?>?) {
        bin = value
    }

    fun bins(value: Boolean?) {
        bins = value
    }

    fun example(value: Iterable<String?>?) {
        example = value
    }

    fun examples(value: Boolean?) {
        examples = value
    }

    fun exclude(value: Iterable<String?>?) {
        exclude = value
    }

    fun features(value: Iterable<String?>?) {
        features = value
    }

    fun lib(value: Boolean?) {
        lib = value
    }

    fun messageFormat(value: String?) {
        messageFormat = value
    }

    fun noDefaultFeatures(value: Boolean?) {
        noDefaultFeatures = value
    }

    fun test(value: Iterable<String?>?) {
        test = value
    }

    fun tests(value: Boolean?) {
        tests = value
    }

    fun workspace(value: Boolean?) {
        workspace = value
    }

    fun doc(value: Boolean?) {
        doc = value
    }

    fun release(value: Boolean?) {
        release = value
    }

    fun noFailFast(value: Boolean?) {
        noFailFast = value
    }

    fun noRun(value: Boolean?) {
        noRun = value
    }
}