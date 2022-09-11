package com.foodandservice.presentation.ui.login

sealed class LoginState {
    object Empty : LoginState()
    object Loading : LoginState()
    data class Success(val phone: String) : LoginState()
    data class Error(val message: String) : LoginState()
    data class LoadPhonePrefix(val prefix: String) : LoginState()
}