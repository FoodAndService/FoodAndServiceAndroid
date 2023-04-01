package com.foodandservice.domain.usecases.sign

import com.foodandservice.domain.model.PhoneWithOtp
import com.foodandservice.domain.model.sign.AuthPhaseWithToken
import com.foodandservice.domain.repository.AuthRepository
import com.foodandservice.domain.util.Resource

class SignInSecondPhaseUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(phone: String, sms: String): Resource<AuthPhaseWithToken> {
        return authRepository.signInSecondPhase(PhoneWithOtp(phone, sms))
    }
}