plugins {
    kotlin("jvm") version "1.3.61"
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "0.10.1"
    id("org.jetbrains.dokka") version "0.10.0"
}

object Define {
    const val PLUGIN_VERSION = "0.1.1"
    const val PLUGIN_ID = "com.osm.gradle.plugins.rustic"
    const val PLUGIN_IMPLEMENTATION_CLASS = "com.osm.gradle.plugins.RusticPlugin"
    const val PLUGIN_GROUP = "com.osm.gradle.plugins"
    const val PLUGIN_ARTIFACT = "rustic"
    const val PLUGIN_DISPLAY_NAME = "Rustic"
    const val PLUGIN_DESCRIPTION =
        "You can execute cargo commands from Gradle. This allows you to combine the rich features of Gradle with Cargo's actions."
    const val WEBSITE = "https://www.osamu-storage.info/"
    const val VCS = "https://github.com/sam-osamu/rustic"
    val PLUGIN_TAGS = listOf<String?>("rust")
}

group = Define.PLUGIN_GROUP
version = Define.PLUGIN_VERSION

tasks {
    val dokka by getting(org.jetbrains.dokka.gradle.DokkaTask::class) {
        outputFormat = "html"
        outputDirectory = "$buildDir/javadoc"
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

    tasks {
        getByName("javadoc", Javadoc::class) {
            dependsOn("dokka")
            options.locale = "en_US"
            isFailOnError = false
            source = sourceSets["main"].allJava
        }

        val sourcesJar by creating(Jar::class) {
            dependsOn(JavaPlugin.CLASSES_TASK_NAME)
            group = "build"
            archiveClassifier.set("sources")
            from(sourceSets["main"].allSource)
        }

        val javadocJar by creating(Jar::class) {
            dependsOn("dokka")
            group = "documentation"
            archiveClassifier.set("javadoc")
            from(getByName("dokka", org.jetbrains.dokka.gradle.DokkaTask::class).outputDirectory)
        }

        artifacts {
            add("archives", sourcesJar)
            add("archives", javadocJar)
        }
    }
}

repositories {
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
    mavenCentral()
}

dependencies {
    "implementation"(gradleApi())
    "implementation"(localGroovy())
    "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    "testImplementation"(group = "junit", name = "junit", version = "4.12")
    "testImplementation"(group = "org.jetbrains.kotlin", name = "kotlin-test-junit", version = "1.3.21")
    "testImplementation"(gradleTestKit())
}

gradlePlugin {
    plugins {
        create("rustic") {
            id = Define.PLUGIN_ID
            implementationClass = Define.PLUGIN_IMPLEMENTATION_CLASS
            displayName = Define.PLUGIN_DISPLAY_NAME
        }
    }
}

pluginBundle {
    website = Define.WEBSITE
    vcsUrl = Define.VCS
    description = Define.PLUGIN_DESCRIPTION
    tags = Define.PLUGIN_TAGS
    version = Define.PLUGIN_VERSION

    mavenCoordinates {
        groupId = Define.PLUGIN_GROUP
        artifactId = Define.PLUGIN_ARTIFACT
        version = Define.PLUGIN_VERSION
    }

    tasks {
        getByName("publishPlugins") {
            dependsOn(getByName("sourcesJar"))
            dependsOn(getByName("javadocJar"))
        }
    }
}