package com.foodandservice.domain.usecases.sign

import com.foodandservice.domain.model.InvalidNameFormatException
import com.foodandservice.domain.model.Name
import com.foodandservice.domain.model.auth.AuthPhaseWithToken
import com.foodandservice.domain.repository.AuthRepository
import com.foodandservice.domain.util.ApiResponse

class SignUpFirstPhaseUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(name: String): ApiResponse<AuthPhaseWithToken> {
        if (name.length < 3) throw InvalidNameFormatException()
        else return authRepository.signUpFirstPhase(Name(name))
    }
}