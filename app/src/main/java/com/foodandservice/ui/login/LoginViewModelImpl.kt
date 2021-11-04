package com.foodandservice.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoginViewModelImpl : LoginViewModel() {
    private val state = MutableLiveData<State>()

    override fun login(phone: String) {

    }

    override fun getState(): LiveData<State> {
        return state
    }
}