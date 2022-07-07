package com.foodandservice

import android.app.Application
import com.foodandservice.util.FysPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FoodAndServiceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initSharedPreferences()
    }

    private fun initSharedPreferences() {
        FysPreferences.init(applicationContext)
    }
}