package com.foodandservice.presentation.ui.splash

sealed class SplashState {
    object LoggedIn : SplashState()
    object NotLoggedIn : SplashState()
    object OnboardingNotFinished : SplashState()
    object Idle : SplashState()
    data class Error(val message: String) : SplashState()
}