package com.foodandservice.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object ClientPreferences {
    private var sharedPref: SharedPreferences? = null

    fun init(context: Context) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getOnboardingStatus(): Boolean {
        return sharedPref!!.getBoolean("isOnboardingFinished", false)
    }

    fun saveOnboardingStatus(finished: Boolean) {
        sharedPref!!.edit().putBoolean("isOnboardingFinished", finished).apply()
    }

    fun deleteOnboardingStatus() {
        sharedPref!!.edit().remove("isOnboardingFinished").apply()
    }
}