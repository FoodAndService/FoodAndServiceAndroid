package com.foodandservice.presentation.ui.splash

sealed class SplashState {
    object UserLoggedIn : SplashState()
    object UserNotLoggedIn : SplashState()
    object OnboardingNotFinished : SplashState()
}