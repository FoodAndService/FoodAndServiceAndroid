package com.foodandservice.presentation.ui.login

sealed class LoginState {
    data class Success(val phone: String) : LoginState()
    data class Error(val message: String) : LoginState()
    object Loading : LoginState()
    object Idle : LoginState()
}