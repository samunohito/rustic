package com.osm.gradle.plugins.types.interfaces.options.config

interface IHttp {
    /**
     * HTTP proxy to use for HTTP requests (defaults to none)
     * in libcurl format, e.g., "socks5h://host:port"
     */
    val proxy: String?
    /**
     * Timeout for each HTTP request, in seconds
     */
    val timeout: Int?
    /**
     * Path to Certificate Authority (CA) bundle (optional)
     */
    val cainfo: String?
    /**
     * Indicates whether SSL certs are checked for revocation
     */
    val checkRevoke: String?
    /**
     * Indicates which SSL version or above to use (options are
     * "default", "tlsv1", "tlsv1.0", "tlsv1.1", "tlsv1.2", "tlsv1.3")
     * To better control SSL version, we can even use
     * `ssl-version.min = "..."` and `ssl-version.max = "..."`
     * where "..." is one of the above options. But note these two forms
     * ("setting `ssl-version`" and "setting both `min`/`max`)
     * can't co-exist.
     */
    val sslVersion: String?
    /**
     * Lower threshold for bytes/sec (10 = default, 0 = disabled)
     */
    val lowSpeedLimit: Int?
    /**
     * whether or not to use HTTP/2 multiplexing where possible
     */
    val multiplexint: Boolean?
    /**
     * This setting can be used to help debug what's going on with HTTP requests made
     * by Cargo. When set to `true` then Cargo's normal debug logging will be filled
     * in with HTTP information, which you can extract with
     * `CARGO_LOG=cargo::ops::registry=debug` (and `trace` may print more).
     *
     * Be wary when posting these logs elsewhere though, it may be the case that a
     * header has an authentication token in it you don't want leaked! Be sure to
     * briefly review logs before posting them.
     */
    val debug: Boolean?
}