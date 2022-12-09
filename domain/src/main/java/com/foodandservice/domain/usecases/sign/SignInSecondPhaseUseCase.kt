package com.foodandservice.domain.usecases.sign

import com.foodandservice.domain.model.PhoneWithOtp
import com.foodandservice.domain.model.sign.AuthPhaseWithToken
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.Resource

class SignInSecondPhaseUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(phone: String, sms: String): Resource<AuthPhaseWithToken> {
        return customerRepository.signInSecondPhase(PhoneWithOtp(phone, sms))
    }
}