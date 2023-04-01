plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    // Coroutines core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Paging 3
    implementation("androidx.paging:paging-common-ktx:3.1.1")
}