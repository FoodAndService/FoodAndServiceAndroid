package com.foodandservice.presentation.ui.signup_finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.model.InvalidNameFormatException
import com.foodandservice.domain.usecases.auth.GetUserTokenUseCase
import com.foodandservice.domain.usecases.auth.SaveCurrentPhaseUseCase
import com.foodandservice.domain.usecases.auth.SaveUserTokenUseCase
import com.foodandservice.domain.usecases.sign.SignUpFirstPhaseUseCase
import com.foodandservice.domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpFinishViewModel(
    private val signUpFirstPhaseUseCase: SignUpFirstPhaseUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
    private val saveCurrentPhaseUseCase: SaveCurrentPhaseUseCase
) :
    ViewModel() {
    private val _signUpFinishState = MutableStateFlow<SignUpFinishState>(SignUpFinishState.Idle)
    val signUpFinishState: StateFlow<SignUpFinishState> = _signUpFinishState.asStateFlow()

    fun finishSignup(name: String) {
        viewModelScope.launch {
            val authToken = getUserTokenUseCase()

            try {
                when (val response = signUpFirstPhaseUseCase(authToken, name)) {
                    is Resource.Success -> {
                        response.data?.let {
                            saveUserTokenUseCase(it.authUser)
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