buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.43.1")
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}