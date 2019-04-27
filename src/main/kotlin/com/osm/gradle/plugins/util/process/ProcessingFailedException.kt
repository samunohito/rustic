package com.osm.gradle.plugins.util.process

import java.io.IOException

class ProcessingFailedException : IOException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}