package com.foodandservice.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor() : LoginViewModel() {
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