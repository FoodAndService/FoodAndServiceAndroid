package com.foodandservice.ui.sms_confirm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SmsConfirmViewModelImpl : SmsConfirmViewModel() {
    private val state = MutableLiveData<State>()

    override fun confirmSms(sms: String) {
        if (sms.isEmpty())
            state.value = State.SmsEmptyError
        else if (sms.length != 6)
            state.value = State.SmsFormatError
        else {
            TODO("Backend sms logic")
        }
    }

    override fun getState(): LiveData<State> {
        return state
    }
}