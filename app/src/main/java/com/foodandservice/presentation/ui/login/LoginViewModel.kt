package com.foodandservice.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.sign.SignInFirstPhaseUseCase
import com.foodandservice.domain.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInFirstPhaseUseCase: SignInFirstPhaseUseCase
) : ViewModel() {
    private val _loginState = MutableSharedFlow<LoginState>(replay = 10)
    val loginState: SharedFlow<LoginState> = _loginState.asSharedFlow()

    fun login(phone: String) {
        viewModelScope.launch {
            _loginState.emit(LoginState.Loading)

            when (val response = signInFirstPhaseUseCase(phone)) {
                is Resource.Success -> {
                    _loginState.emit(LoginState.Success(phone))
                }
                is Resource.Failure -> {
                    _loginState.emit(
                        LoginState.Error(
                            response.exception?.message ?: "Something went wrong"
                        )
                    )
                }
            }

            _loginState.emit(LoginState.Idle)
        }
    }
}