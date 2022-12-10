package com.foodandservice.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.sign.SignInFirstPhaseUseCase
import com.foodandservice.domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInFirstPhaseUseCase: SignInFirstPhaseUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun login(phone: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            when (val response = signInFirstPhaseUseCase(phone)) {
                is Resource.Success -> {
                    _loginState.value = LoginState.Success(phone)
                }
                is Resource.Failure -> _loginState.value = LoginState.Error(response.message)
            }

            _loginState.value = LoginState.Idle
        }
    }
}