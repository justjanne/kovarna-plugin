enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "kovarna-plugin"

includeBuild("gradle/convention")

pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
  }

  plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    id("org.jetbrains.dokka") version "1.6.10"
  }
}
