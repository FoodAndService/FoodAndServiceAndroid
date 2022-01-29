package com.foodandservice.ui.signup_finish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupFinishViewModelImpl @Inject constructor(): SignupFinishViewModel() {
    private val state = MutableLiveData<State>()

    override fun finishSignup(fullname: String) {
        if (fullname.isEmpty())
            state.value = State.NameEmptyError
        else if (fullname.length < 5 || fullname.length > 25)
            state.value = State.NameFormatError
        else
            state.value = State.Success
    }

    override fun getState(): LiveData<State> {
        return state
    }
}