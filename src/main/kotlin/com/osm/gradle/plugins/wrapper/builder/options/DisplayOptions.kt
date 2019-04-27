package com.osm.gradle.plugins.wrapper.builder.options

interface DisplayOptions {
    class Verbose : SingleBase(), DisplayOptions {
        override val option: String = "--verbose"
    }

    class Quiet : SingleBase(), DisplayOptions {
        override val option: String = "--quiet"
    }

    class Color(type: Type) : SingleBase(type.text), DisplayOptions {
        override val option: String = "--color"

        enum class Type(val text: String) {
            Auto("auto"),
            Always("always"),
            Never("never")
        }
    }

    class BuildPlan : SingleBase(), DisplayOptions {
        override val option: String = "--build-plan"
    }

    class MessageFormat(type: Type) : SingleBase(type.text), DisplayOptions {
        override val option: String = "--message-format"

        enum class Type(val text: String) {
            Human("human"),
            Json("json"),
            Short("short")
        }
    }
}