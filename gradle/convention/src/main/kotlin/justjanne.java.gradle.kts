plugins {
    java
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

configure<JavaPluginExtension> {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(17))
  }
}
