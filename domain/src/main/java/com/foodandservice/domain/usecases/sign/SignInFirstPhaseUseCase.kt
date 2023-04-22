package com.foodandservice.domain.usecases.sign

import com.foodandservice.domain.model.CustomerPhone
import com.foodandservice.domain.model.InvalidCustomerPhoneException
import com.foodandservice.domain.model.auth.AuthPhase
import com.foodandservice.domain.repository.AuthRepository
import com.foodandservice.domain.util.ApiResponse
import com.foodandservice.domain.util.RegexHelper

class SignInFirstPhaseUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(phone: String): ApiResponse<AuthPhase> {
        return if (!RegexHelper.phoneRegex.containsMatchIn(phone)) ApiResponse.Failure(
            InvalidCustomerPhoneException()
        )
        else {
            authRepository.signInFirstPhase(
                CustomerPhone(phone)
            )
        }
    }
}