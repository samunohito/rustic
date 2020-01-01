package com.osm.gradle.plugins.wrapper.builder.options.cargo

import com.osm.gradle.plugins.wrapper.builder.options.SingleCommandOptionBase

interface DocumentationOptions {
    class Open : SingleCommandOptionBase(),
        DocumentationOptions {
        override val option: String = "--open"
    }

    class NoDeps : SingleCommandOptionBase(),
        DocumentationOptions {
        override val option: String = "--no-deps"
    }

    class DocumentPrivateItems : SingleCommandOptionBase(),
        DocumentationOptions {
        override val option: String = "--document-private-items"
    }
}
