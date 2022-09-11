package com.foodandservice.domain.usecases.sign

import com.foodandservice.domain.model.Phone
import com.foodandservice.domain.model.sign.Phase
import com.foodandservice.domain.repository.CustomerRepository
import com.foodandservice.domain.util.RegexHelper
import com.foodandservice.domain.util.Resource
import javax.inject.Inject

class SignInFirstPhaseUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    suspend operator fun invoke(phone: String): Resource<Phase> {
        return if (RegexHelper.phoneRegex.containsMatchIn(phone))
            customerRepository.signInFirstPhase(Phone(phone))
        else
            Resource.Error("Phone format is not valid")
    }
}