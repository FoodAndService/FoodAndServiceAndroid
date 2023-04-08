package com.foodandservice.presentation.ui.account

sealed class AccountState {
    object Success : AccountState()
    object Loading : AccountState()
    object Idle : AccountState()
    object SignOut : AccountState()
    data class Error(val message: String) : AccountState()
}
