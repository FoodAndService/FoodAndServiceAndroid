package com.foodandservice.domain.usecases.auth

import com.foodandservice.domain.model.CustomerPhone
import com.foodandservice.domain.repository.AuthRepository

class ResendSmsUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(customerPhone: CustomerPhone) =
        authRepository.resendSms(customerPhone = customerPhone)
}