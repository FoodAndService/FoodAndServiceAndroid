package com.foodandservice.ui.sms_confirm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class SmsConfirmViewModel : ViewModel() {
    sealed class State{
        object Success : State()
        object SmsFormatError : State()
        object SmsEmptyError : State()
        object SmsIncorrectError : State()
    }

    abstract fun confirmSms(sms: String)
    abstract fun getState() : LiveData<State>
}