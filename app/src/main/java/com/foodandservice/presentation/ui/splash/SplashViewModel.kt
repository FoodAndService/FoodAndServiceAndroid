package com.foodandservice.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.auth.IsUserLoggedInUseCase
import com.foodandservice.domain.usecases.onboarding.IsOnboardingFinishedUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase,
    private val isOnboardingFinishedUseCase: IsOnboardingFinishedUseCase
) : ViewModel() {
    private val _splashState = MutableSharedFlow<SplashState>(replay = 10)
    val splashState: SharedFlow<SplashState> = _splashState.asSharedFlow()

    init {
        getUserState()
    }

    private fun getUserState() {
        viewModelScope.launch {
            if (isOnboardingFinishedUseCase()) if (isUserLoggedInUseCase()) {
                _splashState.emit(SplashState.UserLoggedIn)
            } else {
                _splashState.emit(SplashState.UserNotLoggedIn)
            }
            else _splashState.emit(SplashState.OnboardingNotFinished)
        }
    }
}