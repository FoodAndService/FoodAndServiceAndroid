package com.foodandservice

import android.app.Application
import com.foodandservice.data.preferences.ClientPreferences

class FoodAndServiceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initSharedPreferences()
    }

    private fun initSharedPreferences() {
        ClientPreferences.init(applicationContext)
    }
}