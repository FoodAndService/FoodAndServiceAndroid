package com.foodandservice.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class SplashViewModel : ViewModel() {
    sealed class State {
        object OnboardingNotFinished : State()
        object LoggedIn : State()
        object NotLoggedIn : State()
    }

    abstract fun getState(): LiveData<State>
}