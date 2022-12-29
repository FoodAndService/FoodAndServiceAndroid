package com.foodandservice.presentation.ui.sms_confirm_sign

sealed class SmsConfirmSignState {
    object SuccessExistentCustomer : SmsConfirmSignState()
    object Loading : SmsConfirmSignState()
    object Idle : SmsConfirmSignState()
    data class SuccessNewCustomer(val authToken: String) : SmsConfirmSignState()
    data class Error(val message: String) : SmsConfirmSignState()
}
