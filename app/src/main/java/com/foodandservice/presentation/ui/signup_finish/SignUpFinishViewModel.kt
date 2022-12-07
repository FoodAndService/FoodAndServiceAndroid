package com.foodandservice.presentation.ui.signup_finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.model.InvalidNameFormatException
import com.foodandservice.domain.usecases.auth.GetCustomerTokenUseCase
import com.foodandservice.domain.usecases.auth.SaveCurrentPhaseUseCase
import com.foodandservice.domain.usecases.auth.SaveCustomerTokenUseCase
import com.foodandservice.domain.usecases.sign.SignUpFirstPhaseUseCase
import com.foodandservice.domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpFinishViewModel(
    private val signUpFirstPhaseUseCase: SignUpFirstPhaseUseCase,
    private val getCustomerTokenUseCase: GetCustomerTokenUseCase,
    private val saveCustomerTokenUseCase: SaveCustomerTokenUseCase,
    private val saveCurrentPhaseUseCase: SaveCurrentPhaseUseCase
) :
    ViewModel() {
    private val _signUpFinishState = MutableStateFlow<SignUpFinishState>(SignUpFinishState.Idle)
    val signUpFinishState: StateFlow<SignUpFinishState> = _signUpFinishState.asStateFlow()

    fun finishSignup(name: String) {
        viewModelScope.launch {
            val authToken = getCustomerTokenUseCase()

            try {
                when (val response = signUpFirstPhaseUseCase(authToken, name)) {
                    is Resource.Success -> {
                        response.data?.let {
                            saveCustomerTokenUseCase(it.authUser)
                            saveCurrentPhaseUseCase(it.currentPhase)
                        }
                        _signUpFinishState.value = SignUpFinishState.Success
                    }
                    is Resource.Error -> _signUpFinishState.value =
                        SignUpFinishState.Error(response.message)
                    is Resource.Loading -> _signUpFinishState.value = SignUpFinishState.Loading
                }
            } catch (e: InvalidNameFormatException) {
                _signUpFinishState.value =
                    SignUpFinishState.Error("Invalid name format")
            }
        }
    }
}