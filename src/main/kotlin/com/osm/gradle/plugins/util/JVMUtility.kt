package com.osm.gradle.plugins.util

object JVMUtility {
    fun getFamily(): String {
        val os = System.getProperty("os.name")
        if (os == null)
            return ""
        else if (os.toLowerCase().startsWith("windows"))
            return "windows"
        else if (os.toLowerCase().startsWith("mac"))
            return "mac"
        else if (os.toLowerCase().startsWith("linux"))
            return "unix"
        return ""
    }

    fun isWindows() {
        getFamily() == "windows"
    }

    fun isLinux() {
        getFamily() == "linux"
    }

    fun isMac() {
        getFamily() == "mac"
    }
}