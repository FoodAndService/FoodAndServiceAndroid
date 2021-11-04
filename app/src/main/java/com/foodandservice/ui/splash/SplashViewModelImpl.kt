package com.foodandservice.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.foodandservice.data.preferences.ClientPreferences
import kotlinx.coroutines.launch

class SplashViewModelImpl : SplashViewModel() {
    private val state = MutableLiveData<State>()
    private val TAG = "SplashVMImpl"

    init {
        getUserState()
    }

    private fun getUserState() {
        val authToken = ClientPreferences.getSession()

        viewModelScope.launch {
            try {
                if (authToken == null)
                    state.value = State.NotLoggedIn
                else if (!ClientPreferences.isOnboardingFinished())
                    state.value = State.OnboardingNotFinished
                else
                    state.value = State.OnboardingNotFinished
            } catch (e: Exception) {
                state.value = State.NotLoggedIn
                Log.e(TAG, e.message, e)
            }
        }
    }

    override fun getState(): LiveData<State> {
        return state
    }
}