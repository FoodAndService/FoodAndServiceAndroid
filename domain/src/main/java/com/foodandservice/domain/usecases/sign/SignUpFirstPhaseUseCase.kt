package com.foodandservice.domain.usecases.sign

import com.foodandservice.domain.model.InvalidNameFormatException
import com.foodandservice.domain.model.Name
import com.foodandservice.domain.model.sign.AuthPhaseWithToken
import com.foodandservice.domain.repository.AuthRepository
import com.foodandservice.domain.util.Resource

class SignUpFirstPhaseUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(name: String): Resource<AuthPhaseWithToken> {
        if (name.length < 3) throw InvalidNameFormatException()
        else return authRepository.signUpFirstPhase(Name(name))
    }
}