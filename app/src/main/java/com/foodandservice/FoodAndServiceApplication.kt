package com.foodandservice

import android.app.Application
import com.foodandservice.data.preferences.ClientPreferences

class FoodAndServiceApplication : Application() {
    private val TAG: String = "FySApplication"

    override fun onCreate() {
        super.onCreate()
        initSharedPreferences()
    }

    private fun initSharedPreferences() {
        ClientPreferences.init(applicationContext)
    }
}