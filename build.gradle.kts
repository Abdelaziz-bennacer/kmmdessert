buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath("com.android.tools.build:gradle:7.0.0-beta03")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.0")
    }
}

group = "fr.abdel"
version = "1.0"

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://dl.bintray.com/ekito/koin")
    }
}