package com.foodandservice.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoginViewModelImpl : LoginViewModel() {
    private val state = MutableLiveData<State>()

    override fun login(phone: String) {
        if (phone.length != 9)
            state.value = State.PhoneFormatError
        else
            state.value = State.Success
    }

    override fun getState(): LiveData<State> {
        return state
    }
}