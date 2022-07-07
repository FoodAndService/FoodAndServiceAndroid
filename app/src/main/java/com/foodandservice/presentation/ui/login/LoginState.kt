package com.foodandservice.presentation.ui.login

sealed class LoginState {
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
    data class LoadPhonePrefix(val prefix: String) : LoginState()
    object Empty : LoginState()
    object Loading : LoginState()
}