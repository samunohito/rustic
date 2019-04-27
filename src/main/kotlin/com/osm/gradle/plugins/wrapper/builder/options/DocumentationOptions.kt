package com.osm.gradle.plugins.wrapper.builder.options

interface DocumentationOptions {
    class Open : SingleBase(), DocumentationOptions {
        override val option: String = "--open"
    }

    class NoDeps : SingleBase(), DocumentationOptions {
        override val option: String = "--no-deps"
    }

    class DocumentPrivateItems : SingleBase(), DocumentationOptions {
        override val option: String = "--document-private-items"
    }
}
