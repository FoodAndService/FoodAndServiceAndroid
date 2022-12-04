package com.foodandservice.domain.usecases.sign

import com.foodandservice.domain.model.InvalidNameFormatException
import com.foodandservice.domain.model.Name
import com.foodandservice.domain.model.sign.PhaseWithAuth
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.Resource

class SignUpFirstPhaseUseCase(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(authToken: String, name: String): Resource<PhaseWithAuth> {
        if (name.length > 3)
            return customerRepository.signUpFirstPhase(authToken, Name(name))
        else throw InvalidNameFormatException()
    }
}