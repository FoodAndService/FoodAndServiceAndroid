package com.foodandservice.ui.signup_finish

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class SignupFinishViewModel : ViewModel() {
    sealed class State {
        object Success : State()
        object NameFormatError : State()
        object NameEmptyError : State()
    }

    abstract fun finishSignup(fullname: String)
    abstract fun getState(): LiveData<State>
}