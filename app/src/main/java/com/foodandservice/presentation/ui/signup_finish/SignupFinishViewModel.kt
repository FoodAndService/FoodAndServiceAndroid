package com.foodandservice.presentation.ui.signup_finish

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignupFinishViewModel @Inject constructor() : ViewModel() {
    private val _signupFinishState = MutableStateFlow<SignupFinishState>(SignupFinishState.Empty)
    val signupFinishState: StateFlow<SignupFinishState> = _signupFinishState.asStateFlow()

    fun finishSignup(fullname: String) {
        if (fullname.isEmpty())
            _signupFinishState.value = SignupFinishState.Error("Name empty error")
        else if (fullname.length < 5 || fullname.length > 30)
            _signupFinishState.value = SignupFinishState.Error("Name format error")
        else
            _signupFinishState.value = SignupFinishState.Success
    }
}