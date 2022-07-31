package com.foodandservice.presentation.ui.sms_confirm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SmsConfirmViewModelImpl @Inject constructor() : ViewModel() {
    private val _smsConfirmState = MutableStateFlow<SmsConfirmState>(SmsConfirmState.Empty)
    val smsConfirmState: StateFlow<SmsConfirmState> = _smsConfirmState.asStateFlow()

    fun confirmSms(sms: String) {
        if (sms.isEmpty())
            _smsConfirmState.value = SmsConfirmState.Error("SMS code is empty")
        else if (sms.length != 6)
            _smsConfirmState.value = SmsConfirmState.Error("SMS format is invalid")
        else {
            TODO("Backend sms logic")
        }
    }
}