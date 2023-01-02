package com.foodandservice.presentation.ui.sms_confirm_auth

sealed class SmsConfirmAuthState {
    object SuccessExistentCustomer : SmsConfirmAuthState()
    object Loading : SmsConfirmAuthState()
    object Idle : SmsConfirmAuthState()
    data class SuccessNewCustomer(val authToken: String) : SmsConfirmAuthState()
    data class Error(val message: String) : SmsConfirmAuthState()
}
