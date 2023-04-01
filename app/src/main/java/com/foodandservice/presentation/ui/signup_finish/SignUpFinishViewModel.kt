package com.foodandservice.presentation.ui.signup_finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foodandservice.domain.model.InvalidNameFormatException
import com.foodandservice.domain.usecases.auth.SaveAuthCurrentPhaseUseCase
import com.foodandservice.domain.usecases.auth.SaveUserTokenUseCase
import com.foodandservice.domain.usecases.sign.SignUpFirstPhaseUseCase
import com.foodandservice.domain.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignUpFinishViewModel(
    private val signUpFirstPhaseUseCase: SignUpFirstPhaseUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
    private val saveAuthCurrentPhaseUseCase: SaveAuthCurrentPhaseUseCase
) : ViewModel() {
    private val _signUpFinishState = MutableSharedFlow<SignUpFinishState>(replay = 10)
    val signUpFinishState: SharedFlow<SignUpFinishState> = _signUpFinishState.asSharedFlow()

    fun finishSignUp(name: String) {
        viewModelScope.launch {
            _signUpFinishState.emit(SignUpFinishState.Loading)

            try {
                when (val response = signUpFirstPhaseUseCase(name)) {
                    is Resource.Success -> {
                        response.data?.let { authPhaseWithToken ->
                            saveUserTokenUseCase(authPhaseWithToken.token)
                            saveAuthCurrentPhaseUseCase(authPhaseWithToken.currentPhase.name.lowercase())
                        }
                        _signUpFinishState.emit(SignUpFinishState.Success)
                    }
                    is Resource.Failure -> {
                        _signUpFinishState.emit(
                            SignUpFinishState.Error(
                                response.exception?.message ?: "Something went wrong"
                            )
                        )
                    }
                }
            } catch (e: InvalidNameFormatException) {
                _signUpFinishState.emit(SignUpFinishState.Error("Invalid name format"))
            }

            _signUpFinishState.emit(SignUpFinishState.Idle)
        }
    }
}