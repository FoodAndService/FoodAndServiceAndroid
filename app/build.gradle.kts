@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.foodandservice"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".dev"
        }
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            applicationIdSuffix = ".release"
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildToolsVersion = "32.1.0-rc1"
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    // Android core & misc
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    // Flipper
    debugImplementation("com.facebook.flipper:flipper:0.187.1")
    debugImplementation("com.facebook.soloader:soloader:0.10.5")
    debugImplementation("com.facebook.flipper:flipper-network-plugin:0.187.1")
    releaseImplementation("com.facebook.flipper:flipper-noop:0.187.1")

    // Chucker
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    // Paging 3
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation("androidx.paging:paging-common-ktx:3.1.1")

    // Location
    implementation("com.github.BirjuVachhani:locus-android:4.1.0")
    implementation("com.google.android.gms:play-services-location:19.0.1")

    // QR scanner
    implementation("com.budiyev.android:code-scanner:2.1.0")

    // Country Code Picker
    implementation("com.hbb20:ccp:2.7.0")

    // Koin
    implementation("io.insert-koin:koin-core:3.3.3")
    implementation("io.insert-koin:koin-android:3.3.3")

    // Lottie
    implementation("com.airbnb.android:lottie:6.0.0")

    // Custom snackbar
    implementation("com.github.kishandonga:custom-snackbar:1.1")

    // Navigation component
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

    // SMS confirmation view
    implementation("com.github.fraggjkee:sms-confirmation-view:1.5.0")

    // Stripe
    implementation("com.stripe:stripe-android:20.19.2")

    // Dots indicator for viewpager
    implementation("com.tbuonomo.andrui:viewpagerdotsindicator:4.1.2")

    // TabLayout synchronizer
    implementation("io.github.ahmad-hamwi:tabsync:1.0.1")

    // Circular Image View
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Retrofit2 + Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.google.code.gson:gson:2.10.1")

    // Room
    implementation("androidx.room:room-runtime:2.5.1")
    implementation("androidx.room:room-ktx:2.5.1")
    implementation("androidx.room:room-common:2.5.1")
    kapt("androidx.room:room-compiler:2.5.1")
}