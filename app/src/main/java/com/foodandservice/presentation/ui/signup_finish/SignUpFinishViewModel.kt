package com.foodandservice.presentation.ui.signup_finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.model.InvalidNameFormatException
import com.foodandservice.domain.usecases.auth.GetCustomerTokenUseCase
import com.foodandservice.domain.usecases.auth.SaveAuthCurrentPhaseUseCase
import com.foodandservice.domain.usecases.auth.SaveUserTokenUseCase
import com.foodandservice.domain.usecases.sign.SignUpFirstPhaseUseCase
import com.foodandservice.domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpFinishViewModel(
    private val signUpFirstPhaseUseCase: SignUpFirstPhaseUseCase,
    private val getCustomerTokenUseCase: GetCustomerTokenUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
    private val saveAuthCurrentPhaseUseCase: SaveAuthCurrentPhaseUseCase
) :
    ViewModel() {
    private val _signUpFinishState = MutableStateFlow<SignUpFinishState>(SignUpFinishState.Idle)
    val signUpFinishState: StateFlow<SignUpFinishState> = _signUpFinishState.asStateFlow()

    fun finishSignup(name: String) {
        viewModelScope.launch {
            _signUpFinishState.value = SignUpFinishState.Loading

            val authToken = getCustomerTokenUseCase()

            try {
                when (val response = signUpFirstPhaseUseCase(authToken, name)) {
                    is Resource.Success -> {
                        response.data?.let { authPhaseWithToken ->
                            saveUserTokenUseCase(authPhaseWithToken.authUser)
                            saveAuthCurrentPhaseUseCase(authPhaseWithToken.currentPhase.name.lowercase())
                        }
                        _signUpFinishState.value = SignUpFinishState.Success
                    }
                    is Resource.Failure -> _signUpFinishState.value =
                        SignUpFinishState.Error(response.message)
                }
            } catch (e: InvalidNameFormatException) {
                _signUpFinishState.value =
                    SignUpFinishState.Error("Invalid name format")
            }
        }
    }
}