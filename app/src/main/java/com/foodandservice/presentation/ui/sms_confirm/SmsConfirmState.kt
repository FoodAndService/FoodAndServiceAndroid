package com.foodandservice.presentation.ui.sms_confirm

sealed class SmsConfirmState {
    object Success : SmsConfirmState()
    object Empty : SmsConfirmState()
    data class Error(val message: String) : SmsConfirmState()
}
