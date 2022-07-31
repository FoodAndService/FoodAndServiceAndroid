package com.foodandservice.presentation.ui.signup_finish

sealed class SignupFinishState {
    object Success : SignupFinishState()
    object Empty : SignupFinishState()
    data class Error(val message: String) : SignupFinishState()
}