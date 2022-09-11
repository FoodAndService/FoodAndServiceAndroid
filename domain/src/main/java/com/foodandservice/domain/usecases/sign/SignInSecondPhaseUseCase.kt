package com.foodandservice.domain.usecases.sign

import com.foodandservice.domain.model.PhoneWithSmsCode
import com.foodandservice.domain.model.sign.PhaseWithAuth
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.Resource
import javax.inject.Inject

class SignInSecondPhaseUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(phone: String, sms: String): Resource<PhaseWithAuth> {
        return customerRepository.signInSecondPhase(PhoneWithSmsCode(phone, sms))
    }
}