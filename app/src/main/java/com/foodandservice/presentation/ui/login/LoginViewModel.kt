package com.foodandservice.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class LoginViewModel : ViewModel() {
    sealed class State {
        object Success : State()
        object PhoneFormatError : State()
    }

    abstract fun login(phone: String)
    abstract fun getState(): LiveData<State>
}