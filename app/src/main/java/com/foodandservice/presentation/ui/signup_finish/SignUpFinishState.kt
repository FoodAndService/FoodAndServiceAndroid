package com.foodandservice.presentation.ui.signup_finish

sealed class SignUpFinishState {
    object Success : SignUpFinishState()
    object Idle : SignUpFinishState()
    object Loading : SignUpFinishState()
    data class Error(val message: String) : SignUpFinishState()
}