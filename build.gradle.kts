plugins {
    eclipse
    idea
    id("justjanne.kotlin")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.jetbrains.dokka")
    id("java-gradle-plugin")
    id("justjanne.publish")
}

version = "0.0.2"
group = "de.justjanne.modding"

gradlePlugin {
    plugins {
        create("kovarna") {
            id = "kovarna-plugin"
            implementationClass = "de.justjanne.modding.kovarna.plugin.KovarnaPlugin"
        }
    }
}

repositories {
    maven(url = "https://maven.minecraftforge.net/")
}

dependencies {
    implementation("net.minecraftforge.gradle:ForgeGradle:5.1.+")
}
