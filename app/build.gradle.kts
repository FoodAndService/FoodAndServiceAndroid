plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
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
        getByName("release") {
            isMinifyEnabled = true
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

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    // QR scanner
    implementation("com.budiyev.android:code-scanner:2.1.0")

    // Google maps
    implementation("com.google.android.gms:play-services-maps:18.1.0")

    // Country Code Picker
    implementation("com.hbb20:ccp:2.6.1")

    // Koin
    implementation("io.insert-koin:koin-core:3.2.2")
    implementation("io.insert-koin:koin-android:3.3.0")

    // Lottie
    implementation("com.airbnb.android:lottie:5.2.0")

    // Navigation component
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

    // SMS confirmation view
    implementation("com.github.fraggjkee:sms-confirmation-view:1.5.0")

    // Dots indicator for viewpager
    implementation("com.tbuonomo.andrui:viewpagerdotsindicator:4.1.2")

    // TabLayout synchronizer
    implementation("io.github.ahmad-hamwi:tabsync:1.0.1")

    // Circular Image View
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.13.0")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.4.3")

    // Retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9")

    // Gson
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}