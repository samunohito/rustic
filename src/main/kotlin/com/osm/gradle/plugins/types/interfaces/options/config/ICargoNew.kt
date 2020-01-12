package com.osm.gradle.plugins.types.interfaces.options.config

interface ICargoNew {
    /**
     * This is your name/email to place in the `authors` section of a new Cargo.toml
     * that is generated. If not present, then `git` will be probed, and if that is
     * not present then `$USER` and `$EMAIL` will be used.
     */
    val name: String?
    /**
     * This is your name/email to place in the `authors` section of a new Cargo.toml
     * that is generated. If not present, then `git` will be probed, and if that is
     * not present then `$USER` and `$EMAIL` will be used.
     */
    val email: String?
    /**
     * By default `cargo new` will initialize a new Git repository. This key can
     * be set to change the version control system used. Valid values are `git`,
     * `hg` (for Mercurial), `pijul`, `fossil`, or `none` to disable this behavior.
     */
    val vcs: String?
}