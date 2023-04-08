package com.foodandservice.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.usecases.auth.SignOutUseCase
import com.foodandservice.domain.usecases.sign.SignInFirstPhaseUseCase
import com.foodandservice.domain.util.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    signOutUseCase: SignOutUseCase, private val signInFirstPhaseUseCase: SignInFirstPhaseUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    init {
        viewModelScope.launch {
            signOutUseCase()
        }
    }

    fun login(phone: String) {
        viewModelScope.launch {
            _loginState.emit(LoginState.Loading)

            when (val response = signInFirstPhaseUseCase(phone)) {
                is ApiResponse.Success -> {
                    _loginState.emit(LoginState.Success(phone))
                }
                is ApiResponse.Failure -> {
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