package com.osm.gradle.plugins.util.log

import org.slf4j.LoggerFactory

val log = LoggerFactory.getLogger("rustic")

fun info(message: String) {
    log.info(message)
}
