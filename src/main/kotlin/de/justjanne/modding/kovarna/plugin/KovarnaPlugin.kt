package de.justjanne.modding.kovarna.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.provider.Property
import java.net.URI

abstract class KovarnaExtension {
    abstract val withKotlin: Property<Boolean>
}

class KovarnaPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val extension = target.extensions.create("kovarna", KovarnaExtension::class.java)

        withForgeGradle(target)
        target.afterEvaluate {
            if (extension.withKotlin.getOrElse(false)) {
                withKovarna(target)
            }
        }
    }

    // Minecraft Forge Generic
    private fun withForgeGradle(target: Project) {
        target.plugins.apply("net.minecraftforge.gradle")
        target.repositories.maven {
            it.url = URI.create("https://maven.minecraftforge.net/")
        }
        target.beforeEvaluate {
            target.extensions.getByType(JavaPluginExtension::class.java).apply {
                sourceSets.findByName("main")?.resources {
                    it.srcDir("src/generated/resources")
                }
            }
            target.tasks.named("publish") {
                it.dependsOn("reobfJar")
            }
        }
    }

    // Kotlin Specific
    private fun withKovarna(target: Project) {
        target.extensions.getByType(ExtraPropertiesExtension::class.java).apply {
            set("kotlin.stdlib.default.dependency", false)
        }
        target.dependencies.add("implementation", "de.justjanne.modding:kovarna:0.0.2")
        target.dependencies.add("implementation", "org.jetbrains.kotlin:kotlin-stdlib:1.6.10")
        target.dependencies.add("implementation", "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.10")
    }
}
