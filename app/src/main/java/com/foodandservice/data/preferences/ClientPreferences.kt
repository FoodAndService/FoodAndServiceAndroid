package com.foodandservice.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object ClientPreferences {
    private var sharedPref: SharedPreferences? = null

    fun init(context: Context) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getSession(): String? {
        return sharedPref!!.getString("authToken", null)
    }

    fun saveSession(authToken: String) {
        sharedPref!!.edit().putString("authToken", authToken).apply()
    }

    fun deleteSession() {
        sharedPref!!.edit().remove("authToken").apply()
    }

    fun isOnboardingFinished(): Boolean {
        return sharedPref!!.getBoolean("isOnboardingFinished", false)
    }

    fun finishOnboarding() {
        sharedPref!!.edit().putBoolean("isOnboardingFinished", true).apply()
    }

    fun deleteOnboardingStatus() {
        sharedPref!!.edit().remove("isOnboardingFinished").apply()
    }
}