package com.foodandservice.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.auth.IsUserLoggedInUseCase
import com.foodandservice.domain.usecases.onboarding.IsOnboardingFinishedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase,
    private val isOnboardingFinishedUseCase: IsOnboardingFinishedUseCase
) :
    ViewModel() {
    private val _splashState = MutableStateFlow<SplashState>(SplashState.Idle)
    val splashState: StateFlow<SplashState> = _splashState.asStateFlow()

    init {
        getUserState()
    }

    private fun getUserState() {
        viewModelScope.launch {
            try {
                if (isOnboardingFinishedUseCase())
                    _splashState.value =
                        if (isUserLoggedInUseCase()) SplashState.UserLoggedIn else SplashState.UserNotLoggedIn
                else
                    _splashState.value = SplashState.OnboardingNotFinished
            } catch (e: Exception) {
                _splashState.value = SplashState.Error(e.message.toString())
            }
        }
    }
}