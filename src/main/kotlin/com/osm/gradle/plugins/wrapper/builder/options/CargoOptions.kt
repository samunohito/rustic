package com.osm.gradle.plugins.wrapper.builder.options

import com.osm.gradle.plugins.wrapper.builder.command.MultipleCommandOptionBase
import com.osm.gradle.plugins.wrapper.builder.command.SingleCommandOptionBase
import java.net.URL

object CargoOptions {
    class Doc : SingleCommandOptionBase() {
        override val option: String = "--doc"
        override val hasValue: Boolean = false
    }

    class Release : SingleCommandOptionBase() {
        override val option: String = "--release"
        override val hasValue: Boolean = false
    }

    class TargetDir(targetDir: java.nio.file.Path) : SingleCommandOptionBase(targetDir.toAbsolutePath().toString()) {
        override val option: String = "--target-dir"
        override val hasValue: Boolean = true
    }

    class Target(target: String) : SingleCommandOptionBase(target) {
        override val option: String = "--triple"
        override val hasValue: Boolean = true
    }

    class Profile : SingleCommandOptionBase() {
        override val option: String = "--profile"
        override val hasValue: Boolean = false
    }

    class Verbose : SingleCommandOptionBase() {
        override val option: String = "--verbose"
        override val hasValue: Boolean = false
    }

    class Quiet : SingleCommandOptionBase() {
        override val option: String = "--quiet"
        override val hasValue: Boolean = false
    }

    class Color(type: Type) : SingleCommandOptionBase(type.text) {
        override val option: String = "--color"
        override val hasValue: Boolean = true

        enum class Type(val text: String) {
            Auto("auto"),
            Always("always"),
            Never("never")
        }
    }

    class BuildPlan : SingleCommandOptionBase() {
        override val option: String = "--build-plan"
        override val hasValue: Boolean = false
    }

    class MessageFormat(type: Type) : SingleCommandOptionBase(type.text) {
        override val option: String = "--message-format"
        override val hasValue: Boolean = true

        enum class Type(val text: String) {
            Human("human"),
            Json("json"),
            Short("short")
        }
    }

    class Open : SingleCommandOptionBase() {
        override val option: String = "--open"
        override val hasValue: Boolean = false
    }

    class NoDeps : SingleCommandOptionBase() {
        override val option: String = "--no-deps"
        override val hasValue: Boolean = false
    }

    class DocumentPrivateItems : SingleCommandOptionBase() {
        override val option: String = "--document-private-items"
        override val hasValue: Boolean = false
    }

    class Features(features: kotlin.collections.List<String?>) :
        SingleCommandOptionBase(features.filterNotNull().filter { it.isNotEmpty() }.joinToString { " " }) {
        override val option: String = "--features"
        override val hasValue: Boolean = true
    }

    class AllFeatures : SingleCommandOptionBase() {
        override val option: String = "--all-features"
        override val hasValue: Boolean = false
    }

    class NoDefaultFeatures : SingleCommandOptionBase() {
        override val option: String = "--no-default-features"
        override val hasValue: Boolean = false
    }

    class Bin : MultipleCommandOptionBase {
        override val option: String = "--bin"
        override val hasValue: Boolean

        constructor() : super(emptyList()) {
            hasValue = false
        }

        constructor(name: kotlin.collections.List<String?>) : super(name.filterNotNull().filter { it.isNotEmpty() }) {
            hasValue = true
        }
    }

    class Lib : SingleCommandOptionBase() {
        override val option: String = "--lib"
        override val hasValue: Boolean = false
    }

    class Edition(name: String) : SingleCommandOptionBase(name) {
        override val option: String = "--edition"
        override val hasValue: Boolean = true
    }

    class Name(name: String) : SingleCommandOptionBase(name) {
        override val option: String = "--name"
        override val hasValue: Boolean = true
    }

    class Vcs(name: String) : SingleCommandOptionBase(name) {
        override val option: String = "--vcs"
        override val hasValue: Boolean = true
    }

    class Registry(registry: String) : SingleCommandOptionBase(registry) {
        override val option: String = "--registry"
        override val hasValue: Boolean = true
    }

    class Version(version: String) : SingleCommandOptionBase(version) {
        override val option: String = "--vesion"
        override val hasValue: Boolean = false
    }

    class Git(url: URL) : SingleCommandOptionBase(url.toString()) {
        override val option: String = "--git"
        override val hasValue: Boolean = true
    }

    class Branch(name: String) : SingleCommandOptionBase(name) {
        override val option: String = "--branch"
        override val hasValue: Boolean = true
    }

    class Tag(name: String) : SingleCommandOptionBase(name) {
        override val option: String = "--tag"
        override val hasValue: Boolean = true
    }

    class Rev(sha: String) : SingleCommandOptionBase(sha) {
        override val option: String = "--rev"
        override val hasValue: Boolean = true
    }

    class Path(path: java.nio.file.Path) : SingleCommandOptionBase(path.toAbsolutePath().toString()) {
        override val option: String = "--path"
        override val hasValue: Boolean = true
    }

    class List : SingleCommandOptionBase() {
        override val option: String = "--list"
        override val hasValue: Boolean = false
    }

    class Force : SingleCommandOptionBase() {
        override val option: String = "--force"
        override val hasValue: Boolean = false
    }

    class Bins : SingleCommandOptionBase() {
        override val option: String = "--bins"
        override val hasValue: Boolean = false
    }

    class Example(name: kotlin.collections.List<String?>) :
        MultipleCommandOptionBase(name.filterNotNull().filter { it.isNotEmpty() }) {
        override val option: String = "--example"
        override val hasValue: Boolean = true
    }

    class Examples : SingleCommandOptionBase() {
        override val option: String = "--examples"
        override val hasValue: Boolean = false
    }

    class Root(name: java.nio.file.Path) : SingleCommandOptionBase(name.toAbsolutePath().joinToString { " " }) {
        override val option: String = "--root"
        override val hasValue: Boolean = true
    }

    class ManifestPath(path: java.nio.file.Path) : SingleCommandOptionBase(path.toAbsolutePath().toString()) {
        override val option: String = "--manifest-path"
        override val hasValue: Boolean = true
    }

    class Frozen : SingleCommandOptionBase() {
        override val option: String = "--frozen"
        override val hasValue: Boolean = false
    }

    class Locked : SingleCommandOptionBase() {
        override val option: String = "--locked"
        override val hasValue: Boolean = false
    }

    class Jobs(jobs: Int) : SingleCommandOptionBase(jobs.toString()) {
        override val option: String = "--jobs"
        override val hasValue: Boolean = true
    }

    class OutDir(outDir: java.nio.file.Path) : SingleCommandOptionBase(outDir.toAbsolutePath().toString()) {
        override val option: String = "--out-dir"
        override val hasValue: Boolean = true
    }

    class Package(name: kotlin.collections.List<String>) : MultipleCommandOptionBase(name) {
        override val option: String = "--package"
        override val hasValue: Boolean = true
    }

    class All : SingleCommandOptionBase() {
        override val option: String = "--all"
        override val hasValue: Boolean = false
    }

    class Exclude(name: kotlin.collections.List<String>) : MultipleCommandOptionBase(name) {
        override val option: String = "--exclude"
        override val hasValue: Boolean = true
    }

    class Test(name: kotlin.collections.List<String>) : MultipleCommandOptionBase(name) {
        override val option: String = "--test"
        override val hasValue: Boolean = true
    }

    class Tests : SingleCommandOptionBase() {
        override val option: String = "--tests"
        override val hasValue: Boolean = false
    }

    class Bench(name: kotlin.collections.List<String>) : MultipleCommandOptionBase(name) {
        override val option: String = "--bench"
        override val hasValue: Boolean = true
    }

    class Benches : SingleCommandOptionBase() {
        override val option: String = "--benches"
        override val hasValue: Boolean = false
    }

    class AllTargets : SingleCommandOptionBase() {
        override val option: String = "--all-targets"
        override val hasValue: Boolean = false
    }

    class NoRun : SingleCommandOptionBase() {
        override val option: String = "--no-run"
        override val hasValue: Boolean = false
    }

    class NoFailFast : SingleCommandOptionBase() {
        override val option: String = "--no-fail-fast"
        override val hasValue: Boolean = false
    }

    class Help : SingleCommandOptionBase() {
        override val option: String = "--help"
        override val hasValue: Boolean = false
    }
}