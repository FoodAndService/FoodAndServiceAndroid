package com.foodandservice.presentation.ui.sms_confirm

sealed class SmsConfirmState {
    object SuccessExistentCustomer : SmsConfirmState()
    object Loading : SmsConfirmState()
    object Idle : SmsConfirmState()
    data class SuccessNewCustomer(val authToken: String) : SmsConfirmState()
    data class Error(val message: String) : SmsConfirmState()
}
