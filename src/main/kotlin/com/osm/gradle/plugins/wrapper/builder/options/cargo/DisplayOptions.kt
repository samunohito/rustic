package com.osm.gradle.plugins.wrapper.builder.options.cargo

import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase

interface DisplayOptions {
    class Verbose : SingleCommandOptionBase(),
        DisplayOptions {
        override val option: String = "--verbose"
    }

    class Quiet : SingleCommandOptionBase(),
        DisplayOptions {
        override val option: String = "--quiet"
    }

    class Color(type: Type) : SingleCommandOptionBase(type.text),
        DisplayOptions {
        override val option: String = "--color"

        enum class Type(val text: String) {
            Auto("auto"),
            Always("always"),
            Never("never")
        }
    }

    class BuildPlan : SingleCommandOptionBase(),
        DisplayOptions {
        override val option: String = "--build-plan"
    }

    class MessageFormat(type: Type) : SingleCommandOptionBase(type.text),
        DisplayOptions {
        override val option: String = "--message-format"

        enum class Type(val text: String) {
            Human("human"),
            Json("json"),
            Short("short")
        }
    }
}