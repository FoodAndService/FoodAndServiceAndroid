package com.foodandservice.domain.usecases.stripe

import com.foodandservice.domain.repository.StripeRepository
import com.foodandservice.domain.util.Resource

class GetStripeCustomerUseCase(private val stripeRepository: StripeRepository) {
    suspend operator fun invoke(): Resource<String> {
        return stripeRepository.getStripeCustomer()
    }
}