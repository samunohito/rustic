package com.osm.gradle.plugins.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.Marker

interface LoggerSupport {
    val logger: Logger
        get() = LoggerFactory.getLogger(javaClass)

    fun isErrorEnabled(): Boolean = logger.isErrorEnabled
    fun isErrorEnabled(p0: Marker?): Boolean = logger.isErrorEnabled(p0)
    fun isDebugEnabled(): Boolean = logger.isDebugEnabled
    fun isDebugEnabled(p0: Marker?): Boolean = logger.isDebugEnabled(p0)
    fun isInfoEnabled(): Boolean = logger.isInfoEnabled
    fun isInfoEnabled(p0: Marker?): Boolean = logger.isInfoEnabled(p0)
    fun isWarnEnabled(): Boolean = logger.isWarnEnabled
    fun isWarnEnabled(p0: Marker?): Boolean = logger.isWarnEnabled(p0)
    fun isTraceEnabled(): Boolean = logger.isTraceEnabled
    fun isTraceEnabled(p0: Marker?): Boolean = logger.isTraceEnabled(p0)

    fun warn(p0: String?) = logger.warn(p0)
    fun warn(p0: String?, p1: Any?) = logger.warn(p0, p1)
    fun warn(p0: String?, vararg p1: Any?) = logger.warn(p0, p1)
    fun warn(p0: String?, p1: Any?, p2: Any?) = logger.warn(p0, p1, p2)
    fun warn(p0: String?, p1: Throwable?) = logger.warn(p0, p1)
    fun warn(p0: Marker?, p1: String?) = logger.warn(p0, p1)
    fun warn(p0: Marker?, p1: String?, p2: Any?) = logger.warn(p0, p1, p2)
    fun warn(p0: Marker?, p1: String?, p2: Any?, p3: Any?) = logger.warn(p0, p1, p2, p3)
    fun warn(p0: Marker?, p1: String?, vararg p2: Any?) = logger.warn(p0, p1, p2)
    fun warn(p0: Marker?, p1: String?, p2: Throwable?) = logger.warn(p0, p1, p2)

    fun info(p0: String?) = logger.info(p0)
    fun info(p0: String?, p1: Any?) = logger.info(p0, p1)
    fun info(p0: String?, p1: Any?, p2: Any?) = logger.info(p0, p1, p2)
    fun info(p0: String?, vararg p1: Any?) = logger.info(p0, p1)
    fun info(p0: String?, p1: Throwable?) = logger.info(p0, p1)
    fun info(p0: Marker?, p1: String?) = logger.info(p0, p1)
    fun info(p0: Marker?, p1: String?, p2: Any?) = logger.info(p0, p1, p2)
    fun info(p0: Marker?, p1: String?, p2: Any?, p3: Any?) = logger.info(p0, p1, p2, p3)
    fun info(p0: Marker?, p1: String?, vararg p2: Any?) = logger.info(p0, p1, p2)
    fun info(p0: Marker?, p1: String?, p2: Throwable?) = logger.info(p0, p1, p2)

    fun error(p0: String?) = logger.error(p0)
    fun error(p0: String?, p1: Any?) = logger.error(p0, p1)
    fun error(p0: String?, p1: Any?, p2: Any?) = logger.error(p0, p1, p2)
    fun error(p0: String?, vararg p1: Any?) = logger.error(p0, p1)
    fun error(p0: String?, p1: Throwable?) = logger.error(p0, p1)
    fun error(p0: Marker?, p1: String?) = logger.error(p0, p1)
    fun error(p0: Marker?, p1: String?, p2: Any?) = logger.error(p0, p1, p2)
    fun error(p0: Marker?, p1: String?, p2: Any?, p3: Any?) = logger.error(p0, p1, p2, p3)
    fun error(p0: Marker?, p1: String?, vararg p2: Any?) = logger.error(p0, p1, p2)
    fun error(p0: Marker?, p1: String?, p2: Throwable?) = logger.error(p0, p1, p2)

    fun debug(p0: String?) = logger.debug(p0)
    fun debug(p0: String?, p1: Any?) = logger.debug(p0, p1)
    fun debug(p0: String?, p1: Any?, p2: Any?) = logger.debug(p0, p1, p2)
    fun debug(p0: String?, vararg p1: Any?) = logger.debug(p0, p1)
    fun debug(p0: String?, p1: Throwable?) = logger.debug(p0, p1)
    fun debug(p0: Marker?, p1: String?) = logger.debug(p0, p1)
    fun debug(p0: Marker?, p1: String?, p2: Any?) = logger.debug(p0, p1, p2)
    fun debug(p0: Marker?, p1: String?, p2: Any?, p3: Any?) = logger.debug(p0, p1, p2, p3)
    fun debug(p0: Marker?, p1: String?, vararg p2: Any?) = logger.debug(p0, p1, p2)
    fun debug(p0: Marker?, p1: String?, p2: Throwable?) = logger.debug(p0, p1, p2)

    fun trace(p0: String?) = logger.trace(p0)
    fun trace(p0: String?, p1: Any?) = logger.trace(p0, p1)
    fun trace(p0: String?, p1: Any?, p2: Any?) = logger.trace(p0, p1, p2)
    fun trace(p0: String?, vararg p1: Any?) = logger.trace(p0, p1)
    fun trace(p0: String?, p1: Throwable?) = logger.trace(p0, p1)
    fun trace(p0: Marker?, p1: String?) = logger.trace(p0, p1)
    fun trace(p0: Marker?, p1: String?, p2: Any?) = logger.trace(p0, p1, p2)
    fun trace(p0: Marker?, p1: String?, p2: Any?, p3: Any?) = logger.trace(p0, p1, p2, p3)
    fun trace(p0: Marker?, p1: String?, vararg p2: Any?) = logger.trace(p0, p1, p2)
    fun trace(p0: Marker?, p1: String?, p2: Throwable?) = logger.trace(p0, p1, p2)
}