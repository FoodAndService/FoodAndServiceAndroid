package com.foodandservice.domain.usecases.sign

import com.foodandservice.domain.model.CustomerPhone
import com.foodandservice.domain.model.InvalidCustomerPhoneException
import com.foodandservice.domain.model.sign.AuthPhase
import com.foodandservice.domain.repository.AuthRepository
import com.foodandservice.domain.util.RegexHelper
import com.foodandservice.domain.util.Resource

class SignInFirstPhaseUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(phone: String): Resource<AuthPhase> {
        return if (!RegexHelper.phoneRegex.containsMatchIn(phone)) Resource.Failure(
            InvalidCustomerPhoneException()
        )
        else {
            authRepository.signInFirstPhase(
                CustomerPhone(phone)
            )
        }
    }
}