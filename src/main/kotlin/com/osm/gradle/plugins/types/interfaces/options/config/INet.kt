package com.osm.gradle.plugins.types.interfaces.options.config

interface INet {
    /**
     * number of times a network call will automatically retried
     */
    val retry: Int?
    /**
     * if `true` we'll use `git`-the-CLI to fetch git repos
     */
    val gitFetchWithCli: Boolean?
    /**
     * do not access the network, but otherwise try to proceed if possible
     */
    val offline: Boolean?
}