package com.foodandservice

import android.app.Application
import com.foodandservice.util.AndroidLoggingHandler
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FoodAndServiceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidLoggingHandler.setup()
    }
}