package com.foodandservice.presentation.ui.login

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val phone: String) : LoginState()
    data class Error(val message: String) : LoginState()
}