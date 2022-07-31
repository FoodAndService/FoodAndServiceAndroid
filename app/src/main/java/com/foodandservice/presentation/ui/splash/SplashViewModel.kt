package com.foodandservice.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.util.FysPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _splashState = MutableStateFlow<SplashState>(SplashState.Empty)
    val splashState: StateFlow<SplashState> = _splashState.asStateFlow()

    init {
        getUserState()
    }

    private fun getUserState() {
        val authToken = FysPreferences.getSession()

        viewModelScope.launch {
            try {
                if (!FysPreferences.isOnboardingFinished())
                    _splashState.value = SplashState.OnboardingNotFinished
                else if (authToken == null)
                    _splashState.value = SplashState.NotLoggedIn
                else
                    _splashState.value = SplashState.LoggedIn
            } catch (e: Exception) {
                _splashState.value = SplashState.Error(e.message.toString())
            }
        }
    }
}