package com.foodandservice.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.foodandservice.data.preferences.ClientPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(): SplashViewModel() {
    private val state = MutableLiveData<State>()
    private val TAG = "SplashVMImpl"

    init {
        getUserState()
    }

    private fun getUserState() {
        val authToken = ClientPreferences.getSession()

        viewModelScope.launch {
            try {
                if (!ClientPreferences.isOnboardingFinished())
                    state.value = State.OnboardingNotFinished
                else if (authToken == null)
                    state.value = State.NotLoggedIn
                else
                    state.value = State.LoggedIn
            } catch (e: Exception) {
                state.value = State.NetworkError
                Log.e(TAG, e.message, e)
            }
        }
    }

    override fun getState(): LiveData<State> {
        return state
    }
}