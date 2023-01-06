package com.foodandservice.presentation.ui.signup_finish

sealed class SignUpFinishState {
    object Success : SignUpFinishState()
    object Loading : SignUpFinishState()
    object Idle : SignUpFinishState()
    data class Error(val message: String) : SignUpFinishState()
}