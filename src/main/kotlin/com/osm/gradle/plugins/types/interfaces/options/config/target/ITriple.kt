package com.osm.gradle.plugins.types.interfaces.options.config.target

/**
 * For the following sections, $triple refers to any valid target triple, not the
 * literal string "$triple", and it will apply whenever that target triple is
 * being compiled to. 'cfg(...)' refers to the Rust-like `#\[cfg]` syntax for
 * conditional compilation.
 */
interface ITriple {
    /**
     * This is the linker which is passed to rustc (via `-C linker=`) when the `$triple`
     * is being compiled for. By defaulhis flag is not passed to the compiler.
     */
    val linker: String?
    /**
     * Same but for the library archiver which is passed to rustc via `-C ar=`.
     */
    val ar: String?
    /**
     * If a runner is provided, compiled targets for the `$triple` will be executed
     * by invoking the specified runner executable with actual target as first argument.
     * This applies to `cargo run`, `cargo test` and `cargo bench` commands.
     * By default compiled targets are executed directly.
     */
    val runner: String?
    /**
     * custom flags to pass to all compiler invocations that target $triple
     * this value overrides build.rustflags when both are present
     */
    val rustFlags: Iterable<String?>?
}