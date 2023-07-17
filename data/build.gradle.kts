plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":domain"))

    // Coroutines core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Paging 3
    implementation("androidx.paging:paging-common-ktx:3.1.1")
    implementation("androidx.paging:paging-common-ktx:3.1.1")

    // DataStore
    implementation("androidx.datastore:datastore-preferences-core:1.0.0")

    // Room
    implementation("androidx.room:room-runtime:2.5.1")
    implementation("androidx.room:room-ktx:2.5.1")
    implementation("androidx.room:room-common:2.5.1")
    kapt("androidx.room:room-compiler:2.5.1")
}